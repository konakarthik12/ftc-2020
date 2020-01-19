package org.firstinspires.ftc.teamcode.test

import android.os.Environment
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOELoopedOpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import java.io.File

@TeleOp
class OdoRawVals : CompTeleOp() {
    val sd_main = File(Environment.getExternalStorageDirectory().absolutePath + "/forward.txt")
    val writer = sd_main.printWriter()
    override fun initOpMode() {
        super.initOpMode()
        robot.intake.rightIntakeMotor.resetEncoder()
        robot.intake.rightIntakeMotor.resetEncoder()
    }

    override fun mainLoop() {
//        super.mainLoop()
        val angle = robot.gyro.angle
        val rightForwardValue = robot.odometry.rightForwardWheel.getRawValue()
        val strafe = robot.odometry.strafeWheel.getRawValue()
        telemetry.addData("rightFoward", rightForwardValue)
        telemetry.addData("strafe", strafe)
        robot.chassis.turnPower(0.25)
        writer.println("$angle\t$rightForwardValue\t$strafe")

    }

    override fun stop() {
        writer.close()
    }
}