package org.firstinspires.ftc.teamcode.test

import android.os.Environment
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOELoopedOpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.WrapperHandler
import java.io.File

@TeleOp
class OdoRawVals : CompTeleOp() {
    val odo_sd_main = File(Environment.getExternalStorageDirectory().absolutePath + "/forward.txt")
    val odo_writer = odo_sd_main.printWriter()

    override fun initOpMode() {
        super.initOpMode()
        robot.intake.rightIntakeMotor.resetEncoder()
        robot.chassis.backRightMotor.resetEncoder()
//        robot.odometry.launchLoop()
    }

    val angleWrapped = WrapperHandler(360.0) { robot.gyro.getRawAngle() }
    override fun mainLoop() {
        super.mainLoop()
        val angle = robot.gyro.angle
        val rightForwardValue = robot.odometry.rightForwardWheel
        val strafe = robot.odometry.strafeWheel
        val wrappedAngle = angleWrapped.getValue()
        telemetry.addData("rightFowardCorrected", rightForwardValue.getTurnCorrectedValue(wrappedAngle))
        telemetry.addData("strafeCorrected", strafe.getTurnCorrectedValue(wrappedAngle))
        telemetry.addData("rightFoward", rightForwardValue.getRawValue())
        telemetry.addData("strafe", strafe.getRawValue())
        telemetry.addData("gyro", wrappedAngle)
        odo_writer.println("$angle\t$rightForwardValue\t$strafe")
    }

    override fun stop() {
        odo_writer.close()
    }
}