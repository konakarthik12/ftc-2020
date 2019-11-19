package org.firstinspires.ftc.robotcontroller.moeglobal.slam;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.usb.*;
import android.util.Log;

import java.util.*;

import static org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef;
import static org.firstinspires.ftc.robotcontroller.moeglobal.slam.Constants.CHUNK_SIZE;
import static org.firstinspires.ftc.robotcontroller.moeglobal.slam.Constants.SMALL_TIMEOUT;

public class SlamUsbHandler {
    public static UsbManager usbManager;

    public static void uploadByteArray(UsbDevice device, byte[] arr) {
        UsbDeviceConnection deviceConnection = getDeviceConnection(device);
        UsbInterface usbInterface = getUsbInterface(device);
        UsbEndpoint outgoingEndpoint = getOutgoingEndpoint(usbInterface);
        sendToEndpoint(deviceConnection, usbInterface, outgoingEndpoint, arr);
    }

    private static void sendToEndpoint(UsbDeviceConnection connection, UsbInterface usbInterface, UsbEndpoint outPoint, byte[] arr) {
        if (!connection.claimInterface(usbInterface, true)) {
            throw new IllegalStateException("Failed to claim Usb Interface");
        }

        List<byte[]> listToBeSplit = divideArray(arr, CHUNK_SIZE);
        for (byte[] bytes : listToBeSplit) {
            connection.bulkTransfer(outPoint, bytes, CHUNK_SIZE, SMALL_TIMEOUT);
        }

        connection.releaseInterface(usbInterface);
        connection.close();
    }

    private static List<byte[]> divideArray(byte[] source, int chunkSize) {

        List res = new ArrayList();
        int start = 0;

        for (int i = 0; i < (int) Math.ceil(source.length / (double) chunkSize); i++) {
            res.add(Arrays.copyOfRange(source, start, start + chunkSize));
            start += chunkSize;
        }

        return res;
    }

    private static UsbInterface getUsbInterface(UsbDevice device) {
        return device.getInterface(0);
    }

    private static UsbEndpoint getOutgoingEndpoint(UsbInterface usbInterface) {
        for (int k = 0; k < usbInterface.getEndpointCount(); k++) {
            UsbEndpoint uep = usbInterface.getEndpoint(k);
            if (uep.getDirection() == UsbConstants.USB_DIR_OUT) {
                return uep;
            }
        }
        throw new IllegalStateException("Outgoing endpoint not found");
    }

    public static UsbDeviceConnection getDeviceConnection(UsbDevice device) {
        UsbDeviceConnection connection = usbManager.openDevice(device);
        if (connection == null) throw new IllegalStateException("Failed to Open Device");
        return connection;
    }

    public static UsbDevice getDevice(int vid) {
        boolean b = activityRef.get().getPackageManager().hasSystemFeature(PackageManager.FEATURE_USB_HOST);
        boolean b1 = activityRef.get().getPackageManager().hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY);
        UsbAccessory[] accessoryList = usbManager.getAccessoryList();
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
//        usbManager.openDevice()
//        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);

//        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while (deviceIterator.hasNext()) {
            UsbDevice device = deviceIterator.next();

//            manager.requestPermission(device, mPermissionIntent);
            String Model = device.getDeviceName();

            int DeviceID = device.getDeviceId();
            int Vendor = device.getVendorId();
            int Product = device.getProductId();
            int Class = device.getDeviceClass();
            int Subclass = device.getDeviceSubclass();

        }
        Log.e("devices", String.valueOf(deviceList.size()));
        for (UsbDevice device : deviceList.values()) {
            Log.e("devices", String.valueOf(device.getVendorId()));
        }
        for (UsbDevice device : deviceList.values()) {
            if (device.getVendorId() == vid) {
                return device;
            }
        }
        return null;
    }

    static void init(Context context) {
        usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
    }

}
