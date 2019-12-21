package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import android.hardware.usb.UsbDevice
import android.util.Log

object SlamUsbListener {
    private const val TAG = "MOE"
    fun handleDeviceRemoved(device: UsbDevice) {
        Log.e("device", "removed")
        if (device.vendorId == Constants.T265_VID) {
            val t265Handler = SlamHandler.t265Handler
            t265Handler?.killStream()
        }
    }

    fun handleDeviceAdded(device: UsbDevice) {
        Log.e("slam added", device.vendorId.toString())
        if (device.vendorId == Constants.LIZARD_VID) {
            Log.e("Lizard added", Constants.LIZARD_VID.toString())
            handleLizardDeviceAdded(device)
        } else if (device.vendorId == Constants.T265_VID) { //            Log.e("Actual added", String.valueOf(LIZARD_VID));
            handleT265DeviceAdded(device)
        }
    }

    @Synchronized
    fun handleLizardDeviceAdded(device: UsbDevice) {
        SlamLizardHandler.uploadFirmware(device)
    }

    @Synchronized
    fun handleT265DeviceAdded(device: UsbDevice?) {
        val t265Handler = SlamHandler.t265Handler
        t265Handler?.killStream()
        SlamHandler.t265Handler = SlamT265Handler(device!!)
    }
}