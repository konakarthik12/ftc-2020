package org.firstinspires.ftc.robotcontroller.moeglobal.slam;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;

import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_ATTACHED;
import static android.hardware.usb.UsbManager.ACTION_USB_DEVICE_DETACHED;
import static org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamUsbListener.handleLizardDeviceAdded;

public class SlamHandler {
    public static volatile SlamT265Handler t265Handler;
    public static IntentFilter USBAddOrRemove = new IntentFilter();
//    private static SlamUsbListener usbReceiver = new SlamUsbListener();

    static {
        USBAddOrRemove.addAction(ACTION_USB_DEVICE_ATTACHED);
        USBAddOrRemove.addAction(ACTION_USB_DEVICE_DETACHED);
    }

    public static void init(Context context) {
        SlamUsbHandler.init(context);
        SlamLizardHandler.init(context);
        checkPreConnection();
    }

    /**
     * checks if devices are already connected
     */
    private static void checkPreConnection() {
        Log.e("checking", "connection");
        UsbDevice device = SlamLizardHandler.getDevice();
        if (device != null) {
            SlamUsbListener.handleLizardDeviceAdded(device);
            Log.e("slam", "lizard");
        } else {
            Log.e("no lizard", "np");

            device = SlamT265Handler.Companion.getDevice();
            if (device != null) {
                Log.e("slam", "legit");

                SlamUsbListener.handleT265DeviceAdded(device);
            }
        }
    }

//    public static void initUsbListener(Context context) {
//        context.registerReceiver(usbReceiver, USBAddOrRemove);
//    }

//    public static void unRegisterListener(Context context) {
//        context.unregisterReceiver(usbReceiver);
//    }

    public static void handleDeviceAdded(UsbDevice usbDevice) {
        SlamUsbListener.handleDeviceAdded(usbDevice);

    }

    public static void handleDeviceRemoved(UsbDevice usbDevice) {
        SlamUsbListener.handleDeviceRemoved(usbDevice);
    }
}
