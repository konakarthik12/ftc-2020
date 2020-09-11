package org.firstinspires.ftc.teamcode.test

import android.content.Context
import android.hardware.usb.UsbManager


import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@Disabled
@TeleOp
class USBHostTest : MOEAuton() {


    override fun initOpMode() {

        //        telemetry.update()
        //        telemetry.addData("testagain")
        //        robot.odometry.servos.initServosUp()
    }

    override fun run() {
        //        robot.chassis.turnPower(gamepad1.left_stick_x.toDouble())
        val appContext = hardwareMap.appContext
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