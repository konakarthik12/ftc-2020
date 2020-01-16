package org.firstinspires.ftc.teamcode.test

import android.os.Environment
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.external.other.WrapperHandler
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import java.io.File
import java.io.PrintWriter

@TeleOp(name = "MOEdometry test")
class RawOdometryTest : CompTeleOp() {
    val sd_main = File(Environment.getExternalStorageDirectory().absolutePath + "/forward.txt")
    val writer = sd_main.printWriter()
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    override fun initOpMode() {
        super.initOpMode()
        telemetry.addData("test")
//        robot.odometry.servos.initServosDown()
    }

    override fun log() {
        val rightForwardValue = robot.odometry.rightForwardWheel.getScaledValue()
        val angle = robot.gyro.angle
        robot.odometry.angleWrapped
        val angleWrapped = robot.odometry.angleWrappedValue
        val angleWrapped2 = WrapperHandler(360) {angle}.getValue()
        telemetry.addData("forward", rightForwardValue)

        val strafe = robot.odometry.strafeWheel.getScaledValue()
        telemetry.addData("strafe", strafe)
        telemetry.addData("angle", angle)
        telemetry.addData("wrapped angle", angleWrapped)
        telemetry.addData("wrapped angle", angleWrapped2)
//              sd_main.  .use { out -> out.println(fileContent) }
        telemetry.update()
        writer.println("$angle\t$rightForwardValue\t$strafe")
    }

    override fun stop() {
        writer.close()
    }
}