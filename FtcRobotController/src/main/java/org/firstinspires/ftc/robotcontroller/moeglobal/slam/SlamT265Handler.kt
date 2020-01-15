package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import android.graphics.Color
import android.hardware.usb.*
import android.os.Process
import android.util.Log
import android.widget.TextView
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity
import java.nio.ByteBuffer
import java.nio.ByteOrder

private var accuracyText = arrayOf("Failed", "Low", "Medium", "High")
private var warningColors = arrayOf(
        Color.RED, Color.rgb(255, 127, 127),
        Color.rgb(153, 153, 0), Color.GREEN)


@Volatile
private var isRunning = false

class SlamT265Handler internal constructor(device: UsbDevice) {

    private var connection: UsbDeviceConnection? = null
    private var usbInterface: UsbInterface? = null
    private var control: UsbEndpoint? = null
    private var outEndpoint2: UsbEndpoint? = null
    private var outEndpoint3: UsbEndpoint? = null
    private var inEndpoint129: UsbEndpoint? = null
    private var inEndpoint130: UsbEndpoint? = null
    private var inEndpoint131: UsbEndpoint? = null
    private val tempSlam = ByteArray(104)

    //    val commands = LinkedList<ByteArray>()

    //    private void closeConnection() {
    //        connection.releaseInterface(usbInterface);
    //        connection.close();
    //    }
//    val data = SlamData()


    companion object {
        lateinit var myContext: FtcRobotControllerActivity
        val textView: TextView?
            get() = myContext.textStreamingStatus

        @JvmStatic
        fun init(context: FtcRobotControllerActivity) {
            myContext = context
//            textView = context.textStreamingStatus
            //        context.setStreamingText()
        }

        val device: UsbDevice?
            get() = SlamUsbHandler.getDevice(Constants.T265_VID)

        init {
            Log.e("slam", "starting slam thread")
            Thread(SlamRunnable()).start()
        }

    }

    @Volatile
    private var needsRestart = false

    //    init {
    //    }

    private fun initVariables(device: UsbDevice) {
        connection = SlamUsbHandler.getDeviceConnection(device)
        usbInterface = device.getInterface(0)
        loadEndpoints()
        connection!!.claimInterface(usbInterface, true)
        sendInitCode()
        isRunning = true
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
        //        if (!initCodeSent) {
        //            connection!!.claimInterface(usbInterface, true)
        sendInitCode()
        //            initCodeSent = true
        //        }
        isRunning = true
    }

    private fun updateSlam() {
        val order = getBufferFromUSB()
        fillDataIn(order)
    }

    private fun getBufferFromUSB(): ByteBuffer {
        connection!!.bulkTransfer(inEndpoint131, tempSlam, 0, tempSlam.size, 100)
        return ByteBuffer.wrap(tempSlam, 8, tempSlam.size - 8).order(ByteOrder.LITTLE_ENDIAN)
    }

    private fun fillDataIn(order: ByteBuffer) {

        SlamData.fillIn(order)

        //        Log.e("time", Data.lastTimestamp.toString())
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
        Log.e("killing", "dead")
        isRunning = false
        SlamData.confidence = 0
        updateConfidence()
    }

    fun restart() {
        needsRestart = true
    }

    class SlamRunnable : Runnable {
        override fun run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
            while (true) {
                if (isRunning) {
                    SlamHandler.t265Handler?.let {
                        it.checkRestart()
                        it.updateSlam()
                        it.updateUI()
                    }

                } else {
//                    Log.e("sleeping","sleep")
                    Thread.sleep(500)
                }
            }
            //            Log.e("done", "finished")
            //            closeConnection();
        }
    }

    private fun updateUI() {
        updateConfidence()
    }

    private fun updateConfidence() {
//        val textView: TextView? = myContext.textStreamingStatus
        myContext.runOnUiThread {

            textView?.setTextColor(warningColors[SlamData.confidence])
            textView?.text = accuracyText[SlamData.confidence]
        }
    }


    //
    private fun checkCommands() {
        //        commands.pollFirst()?.let { sendCode(it) }

    }

    private fun checkRestart() {
        if (needsRestart) {
            actualRestart()
        }
        needsRestart = false
    }

    private fun actualRestart() {
        Log.e("Restart", "sent")
        sendCode(Constants.DEV_STOP)
        //        while (isRunning) {
        //            updateSlam()
        //            Log.e("info", quatAngle.contentToString())
        //        }
        //        val doubles = quatAngle.copyOf()
        //        var sleep = 100
        //        while (isRunning && quatAngle.contentEquals(doubles)) {
        //            Log.e("restarting", sleep.toString())
        //            //            sendCode(Constants.DEV_STOP)
        //            try {
        //                Thread.sleep(sleep.toLong())
        //                sendInitCode()
        //                Thread.sleep(sleep.toLong())
        //            } catch (e: InterruptedException) {
        //                e.printStackTrace()
        //            }
        //            updateSlam()
        //            sleep += 50
        //        }
    }

    init {
        initVariables(device)
        //        startStream()
    }
}