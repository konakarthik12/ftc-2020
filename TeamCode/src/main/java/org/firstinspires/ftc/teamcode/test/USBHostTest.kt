package org.firstinspires.ftc.teamcode.test

import android.content.Context
import android.hardware.usb.UsbManager


import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEFancyPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.toFixed

@TeleOp(name = "USBHostTest")
class USBHostTest : MOETeleOp() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    override fun initOpMode() {

        //        telemetry.update()
        //        telemetry.addData("testagain")
        //        robot.odometry.servos.initServosUp()
    }

    override fun mainLoop() {
        //        robot.chassis.turnPower(gamepad1.left_stick_x.toDouble())
        val appContext = iHardwareMap.appContext
        val manager = appContext.getSystemService(Context.USB_SERVICE) as UsbManager
        val deviceList = manager.deviceList.map { it.key }
        //
        telemetry.addData(deviceList.toString())
        telemetry.update()
    }

    //    private fun waitForGamepad() {
    //        while (g)
    //    }
}