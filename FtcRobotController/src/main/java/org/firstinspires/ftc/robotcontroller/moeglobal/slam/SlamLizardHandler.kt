package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import android.content.Context
import android.hardware.usb.UsbDevice
import com.sun.tools.javac.util.Assert
import java.io.IOException

object SlamLizardHandler {


    lateinit var firmware: ByteArray
    fun uploadFirmware(device: UsbDevice) {
        SlamUsbHandler.uploadByteArray(device, firmware)
    }

    //    val device: UsbDevice?
    fun getDevice(): UsbDevice? {
        return SlamUsbHandler.getDevice(Constants.LIZARD_VID)
    }

    //        get() = SlamUsbHandler.getDevice(Constants.LIZARD_VID)

    fun init(context: Context) {
        firmware = getFirmware(context)
    }


    private fun getFirmware(context: Context): ByteArray {
        try {
            val firmwareFile = context.assets.open("firmware/target.mvcmd")
            val res = ByteArray(firmwareFile.available())
            val read = firmwareFile.read(res)
            Assert.check(read != -1)
            return res
        } catch (e: IOException) {
            e.printStackTrace()
            throw IllegalStateException("Missing T265 Firmware")
        }
    }
}