package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import android.content.Context
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.util.Log

object SlamHandler {
    @Volatile
    var t265Handler: SlamT265Handler? = null
    var USBAddOrRemove = IntentFilter()
    @JvmStatic
    fun init(context: Context) {
        SlamUsbHandler.init(context)
        SlamLizardHandler.init(context)
        checkPreConnection()
    }

    /**
     * checks if devices are already connected
     */
    private fun checkPreConnection() {
        Log.e("checking", "connection")
        var device = SlamLizardHandler.getDevice()
        if (device != null) {
            SlamUsbListener.handleLizardDeviceAdded(device)
            Log.e("slam", "lizard")
        } else {
            Log.e("no lizard", "np")
            device = SlamT265Handler.device
            if (device != null) {
                Log.e("slam", "legit")
                SlamUsbListener.handleT265DeviceAdded(device)
            }
        }
    }

    //    public static void initUsbListener(Context context) {
    //        context.registerReceiver(usbReceiver, USBAddOrRemove);
    //    }
    //    public static void unRegisterListener(Context context) {
    //        context.unregisterReceiver(usbReceiver);
    //    }
    @JvmStatic
    fun handleDeviceAdded(usbDevice: UsbDevice) {
        SlamUsbListener.handleDeviceAdded(usbDevice)
    }

    @JvmStatic
    fun handleDeviceRemoved(usbDevice: UsbDevice) {
        SlamUsbListener.handleDeviceRemoved(usbDevice)
    }

    //    private static SlamUsbListener usbReceiver = new SlamUsbListener();
    init {
        USBAddOrRemove.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        USBAddOrRemove.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
    }
}