package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.util.Log

class SlamUSBListener : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val usbDevice = intent.getParcelableExtra<UsbDevice>(UsbManager.EXTRA_DEVICE)

        if (UsbManager.ACTION_USB_DEVICE_ATTACHED == intent.action) {
            SlamUsbReciever.handleDeviceAdded(usbDevice);
        } else if (UsbManager.ACTION_USB_DEVICE_DETACHED == intent.action) {
            SlamUsbReciever.handleDeviceRemoved(usbDevice);
        }
        Log.e("why", intent.action)
    }
}