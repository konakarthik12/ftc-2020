package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import android.content.Context
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import android.util.Log
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity

object SlamHandler {
    @Volatile
    var t265Handler: SlamT265Handler? = null
    private val USBListener = SlamUSBListener()
    var USBAddOrRemove = IntentFilter().apply {
        addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
    }


    @JvmStatic
    fun init(context: FtcRobotControllerActivity) {
        SlamUsbHandler.init(context)
        SlamLizardHandler.init(context)
        SlamT265Handler.init(context)
        checkPreConnection()
        initUsbListener(context)
    }

    /**
     * checks if devices are already connected
     */
    private fun checkPreConnection() {
        Log.e("checking", "connection")
        var device = SlamLizardHandler.getDevice()
        if (device != null) {
            SlamUsbReciever.handleLizardDeviceAdded(device)
            Log.e("slam", "lizard")
        } else {
            Log.e("no lizard", "np")
            device = SlamT265Handler.device
            if (device != null) {
                Log.e("slam", "legit")
                SlamUsbReciever.handleT265DeviceAdded(device)
            }
        }
    }

    private fun initUsbListener(context: Context) {
        context.registerReceiver(USBListener, USBAddOrRemove)
    }

    //    public static void unRegisterListener(Context context) {
    //        context.unregisterReceiver(usbReceiver);
    //    }
    //    @JvmStatic
    //    fun handleDeviceAdded(usbDevice: UsbDevice) {
    //        SlamUsbReciever.handleDeviceAdded(usbDevice)
    //    }
    //
    //    @JvmStatic
    //    fun handleDeviceRemoved(usbDevice: UsbDevice) {
    //        SlamUsbReciever.handleDeviceRemoved(usbDevice)
    //    }


}