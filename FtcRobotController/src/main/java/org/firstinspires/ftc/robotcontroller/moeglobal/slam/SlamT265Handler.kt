package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import android.hardware.usb.*
import android.os.Process
import android.util.Log
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*

class SlamT265Handler internal constructor(device: UsbDevice) {
    @Volatile
    private var initCodeSent = false
    private var connection: UsbDeviceConnection? = null
    private var usbInterface: UsbInterface? = null
    private var control: UsbEndpoint? = null
    private var outEndpoint2: UsbEndpoint? = null
    private var outEndpoint3: UsbEndpoint? = null
    private var inEndpoint129: UsbEndpoint? = null
    private var inEndpoint130: UsbEndpoint? = null
    private var inEndpoint131: UsbEndpoint? = null
    private val tempSlam = ByteArray(104)
    //    private void closeConnection() {
    //        connection.releaseInterface(usbInterface);
    //        connection.close();
    //    }
    val curPose = FloatArray(3)
    @Volatile
    private var isRunning = false

    companion object {
        val device: UsbDevice?
            get() = SlamUsbHandler.getDevice(Constants.T265_VID)

        init {
            Log.e("multiple", "whyplz")
            Thread(SlamRunnable()).start()
        }
    }

    val quatAngle = DoubleArray(4)
    @Volatile
    private var needsRestart = false


    private fun initVariables(device: UsbDevice) {
        connection = SlamUsbHandler.getDeviceConnection(device)
        usbInterface = device.getInterface(0)
        loadEndpoints()
    }

    private fun loadEndpoints() {
        for (k in 0 until usbInterface!!.endpointCount) {
            val endpoint = usbInterface!!.getEndpoint(k)
            val outgoing = endpoint.direction == UsbConstants.USB_DIR_OUT
            if (outgoing) {
                when (endpoint.address) {
                    1 -> control = endpoint
                    2 -> outEndpoint2 = endpoint
                    3 -> outEndpoint3 = endpoint
                }
            } else {
                when (endpoint.address) {
                    129 -> if (UsbConstants.USB_DIR_IN == endpoint.direction) inEndpoint129 = endpoint
                    130 -> if (UsbConstants.USB_DIR_IN == endpoint.direction) inEndpoint130 = endpoint
                    131 -> if (UsbConstants.USB_DIR_IN == endpoint.direction) inEndpoint131 = endpoint
                }
            }
        }
    }

    fun startStream() {
        if (!initCodeSent) {
            connection!!.claimInterface(usbInterface, true)
            sendInitCode()
            initCodeSent = true
        }
        isRunning = true
    }

    private fun updateSlam() {
        connection!!.bulkTransfer(inEndpoint131, tempSlam, 0, tempSlam.size, 100)
        val order = ByteBuffer.wrap(tempSlam, 8, 7 * 4).order(ByteOrder.LITTLE_ENDIAN)
        for (i in 0..2) {
            curPose[i] = order.float
        }
        for (i in 0..3) {
            quatAngle[i] = order.float.toDouble()
        }
    }

    private fun sendInitCode() {
        sendCode(Constants.SLAM_CONTROL)
        sendCode(Constants.POSE_CONTROL)
        sendCode(Constants.DEV_START)
    }

    private fun sendCode(arr: ByteArray) {
        val bytes = ByteArray(8)
        connection!!.bulkTransfer(outEndpoint2, arr, arr.size, Constants.SMALL_TIMEOUT)
        connection!!.bulkTransfer(inEndpoint130, bytes, 8, 100)
    }

    fun killStream() {
        isRunning = false
    }

    fun restart() {
        needsRestart = true
    }

    class SlamRunnable : Runnable {
        override fun run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
            while (true) {
                if (SlamHandler.t265Handler?.isRunning == true) {
                    SlamHandler.t265Handler?.updateSlam()
                    SlamHandler.t265Handler?.checkRestart()
                } else{
                    Thread.sleep(500)
                }
            }
//            Log.e("done", "finished")
            //            closeConnection();
        }
    }

    private fun checkRestart() {
        if (needsRestart) { //            sendCode(DEV_STOP);
            //            sendInitCode();
            actualRestart()
        }
        //        try {
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        needsRestart = false
    }

    private fun actualRestart() {
        val doubles = quatAngle.copyOf()
        var sleep = 100
        while (isRunning && quatAngle.contentEquals(doubles)) {
            Log.e("restarting", sleep.toString())
            sendCode(Constants.DEV_STOP)
            try {
                Thread.sleep(sleep.toLong())
                sendInitCode()
                Thread.sleep(sleep.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            updateSlam()
            sleep += 50
        }
    }

    init {
        initVariables(device)
    }
}