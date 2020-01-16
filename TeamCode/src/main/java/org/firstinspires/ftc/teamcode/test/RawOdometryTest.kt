package org.firstinspires.ftc.teamcode.test

import android.os.Environment
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import java.io.File
import java.io.PrintWriter

@TeleOp
class RawOdometryTest : CompTeleOp() {
    val sd_main = File(Environment.getExternalStorageDirectory().absolutePath + "/forward.txt")
    val writer = sd_main.printWriter()
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    override fun initOpMode() {
        telemetry.addData("test")
//        robot.odometry.servos.initServosDown()
    }

    override fun log() {
        val rightForwardValue = robot.odometry.rightFoward.getScaledValue()
        val angle = robot.gyro.angle
        telemetry.addData("forward", rightForwardValue)
        val strafe = robot.odometry.strafe.getScaledValue()
        telemetry.addData("strafe", strafe)
//              sd_main.  .use { out -> out.println(fileContent) }
//        telemetry.update()
        writer.println("$angle\t$rightForwardValue")
    }

    override fun stop() {
        writer.close()
    }
}