package org.firstinspires.ftc.teamcode.test

import android.os.Environment
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.WrapperHandler
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import java.io.File

@TeleOp(name = "MOEdometry test")
class RawOdometryTest : CompTeleOp() {
    val sd_main = File(Environment.getExternalStorageDirectory().absolutePath + "/forward.txt")
    val writer = sd_main.printWriter()
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    override fun initOpMode() {
        super.initOpMode()
        robot.odometry.launchLoop()
        telemetry.addData("test")
//        robot.odometry.servos.initServosDown()
    }

    override fun log() {


        val robotPose = robot.odometry.pose


        val angle = robot.gyro.angle
        val rightForwardValue = robot.odometry.rightForwardWheel.updateValue(angle)
        val strafe = robot.odometry.strafeWheel.updateValue(angle)


        val angleWrapped = robot.odometry.angleWrappedValue
        val fieldCentricPose = robot.odometry.fieldCentricPose

        telemetry.addData("forward", rightForwardValue)
        telemetry.addData("strafe", strafe)
        telemetry.addData("robot pose", robotPose)
        telemetry.addData("angle", angle)
        telemetry.addData("wrapped angle", angleWrapped)
        telemetry.addData("fieldcentric pose", fieldCentricPose)
//              sd_main.  .use { out -> out.println(fileContent) }
        telemetry.update()
        writer.println("$angle\t$rightForwardValue\t$strafe")
    }

    override fun stop() {
        writer.close()
    }
}