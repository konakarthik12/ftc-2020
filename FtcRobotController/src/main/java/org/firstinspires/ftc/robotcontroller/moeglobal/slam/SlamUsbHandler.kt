package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.usb.*
import android.util.Log
import org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder
import org.firstinspires.ftc.robotcontroller.moeglobal.divideArray

object SlamUsbHandler {
    var usbManager: UsbManager? = null
    fun uploadByteArray(device: UsbDevice, arr: ByteArray) {
        val deviceConnection = getDeviceConnection(device)
        val usbInterface = getUsbInterface(device)
        val outgoingEndpoint = getOutgoingEndpoint(usbInterface)
        sendToEndpoint(deviceConnection, usbInterface, outgoingEndpoint, arr)
    }

    private fun sendToEndpoint(connection: UsbDeviceConnection, usbInterface: UsbInterface, outPoint: UsbEndpoint, arr: ByteArray) {
        check(connection.claimInterface(usbInterface, true)) { "Failed to claim Usb Interface" }
        val listToBeSplit = arr.divideArray(Constants.CHUNK_SIZE)
        for (bytes in listToBeSplit) {
            connection.bulkTransfer(outPoint, bytes, Constants.CHUNK_SIZE, Constants.SMALL_TIMEOUT)
        }
        connection.releaseInterface(usbInterface)
        connection.close()
    }


    private fun getUsbInterface(device: UsbDevice): UsbInterface {
        return device.getInterface(0)
    }

    private fun getOutgoingEndpoint(usbInterface: UsbInterface): UsbEndpoint {
        for (k in 0 until usbInterface.endpointCount) {
            val uep = usbInterface.getEndpoint(k)
            if (uep.direction == UsbConstants.USB_DIR_OUT) {
                return uep
            }
        }
        throw IllegalStateException("Outgoing endpoint not found")
    }

    fun getDeviceConnection(device: UsbDevice?): UsbDeviceConnection {
        return usbManager!!.openDevice(device) ?: throw IllegalStateException("Failed to Open Device")
    }

    fun getDevice(vid: Int): UsbDevice? {
//        val b = ActivityReferenceHolder.activityRef.get()!!.packageManager.hasSystemFeature(PackageManager.FEATURE_USB_HOST)
//        val b1 = ActivityReferenceHolder.activityRef.get()!!.packageManager.hasSystemFeature(PackageManager.FEATURE_USB_ACCESSORY)
//        val accessoryList = usbManager!!.accessoryList
        val deviceList = usbManager!!.deviceList
        //        usbManager.openDevice()
        //        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        //        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
//        val deviceIterator: Iterator<UsbDevice> = deviceList.values.iterator()
//        while (deviceIterator.hasNext()) {
//            val device = deviceIterator.next()
            //            manager.requestPermission(device, mPermissionIntent);
//            val Model = device.deviceName
//            val DeviceID = device.deviceId
//            val Vendor = device.vendorId
//            val Product = device.productId
//            val Class = device.deviceClass
//            val Subclass = device.deviceSubclass
//        }
        Log.e("devices", deviceList.size.toString())
        for (device in deviceList.values) {
            Log.e("devices", device.vendorId.toString())
        }
        for (device in deviceList.values) {
            if (device.vendorId == vid) {
                return device
            }
        }
        return null
    }

    fun init(context: Context) {
        usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    }
}