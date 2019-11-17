package org.firstinspires.ftc.robotcontroller.moeglobal.slam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.util.Log;

import static android.hardware.usb.UsbManager.*;
import static org.firstinspires.ftc.robotcontroller.moeglobal.slam.Constants.LIZARD_VID;
import static org.firstinspires.ftc.robotcontroller.moeglobal.slam.Constants.T265_VID;


public class SlamUsbListener extends BroadcastReceiver {
    private static String TAG = "MOE";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        UsbDevice device = intent.getParcelableExtra(EXTRA_DEVICE);
        if (action == null || device == null) return;
        Log.e(TAG, action.substring(action.lastIndexOf('.') + 1) + ": " + device.getVendorId());
        if (action.equals(ACTION_USB_DEVICE_ATTACHED)) {
            handleDeviceAdded(device);
        } else if (action.equals(ACTION_USB_DEVICE_DETACHED)) {
            handleDeviceRemoved(device);
        }
    }

    private void handleDeviceRemoved(UsbDevice device) {
        if (device.getVendorId() == T265_VID) {
            SlamT265Handler t265Handler = SlamHandler.t265Handler;
            if (t265Handler != null) {
                t265Handler.killStream();
            }
        }
    }

    private void handleDeviceAdded(UsbDevice device) {
        Log.e("slam added", String.valueOf(device.getVendorId()));
        if (device.getVendorId() == LIZARD_VID) {
            Log.e("Lizard added", String.valueOf(LIZARD_VID));
            handleLizardDeviceAdded(device);
        } else if (device.getVendorId() == T265_VID) {
//            Log.e("Actual added", String.valueOf(LIZARD_VID));
            handleT265DeviceAdded(device);
        }
    }

    synchronized void handleLizardDeviceAdded(UsbDevice device) {
        SlamLizardHandler.uploadFirmware(device);
    }

    public synchronized void handleT265DeviceAdded(UsbDevice device) {
        SlamHandler.t265Handler = new SlamT265Handler(device);
    }
}
