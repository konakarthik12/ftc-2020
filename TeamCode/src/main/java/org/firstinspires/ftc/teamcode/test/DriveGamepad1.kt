package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.MOEConstants.IntakeSystem
import org.firstinspires.ftc.teamcode.utilities.addData
import kotlin.math.cos
import kotlin.math.sin

@TeleOp(name = "DriveGamepad1")
class DriveGamepad1 : MOETeleOp() {
    override fun initOpMode() {
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")
    }

    override fun mainLoop() {
        telemetry.addData(gamepad1.left_trigger.toDouble())
        telemetry.update()

        robot.outTake.outTakeServo.setPosition(gamepad1.left_trigger.toDouble())

//        harvester()
//        foundation()

//        chassis()
//        other()
    }

    private fun other() {
        if (gamepad1.y) {
            robot.gyro.resetEulerAngle()
        }
    }

    private fun foundation() {
        robot.foundation.foundationServo.setPosition(if (gamepad1.right_bumper) 1.0 else 0.0)
    }


    private fun chassis() {
        val maxPower = 1.0
        val scaleX = 0.8
        val scaleY = 0.75
        val scaleRot = 0.45
        val angle = robot.gyro.eulerAngle
        var rawX = -gamepad1.left_stick_x.toDouble()
        var rawY = (gamepad1.left_stick_y).toDouble()
        var rot = gamepad1.right_stick_x.toDouble()
        rawX *= scaleX
        rawY *= scaleY
        rot *= scaleRot
        val fwd = -rawX * sin(Math.toRadians(angle)) + rawY * cos(Math.toRadians(angle))
        val str = rawX * cos(Math.toRadians(angle)) + rawY * sin(Math.toRadians(angle))
        //        var fwd = rawY
        var FRP = fwd - str - rot
        var FLP = fwd + str + rot
        var BRP = fwd + str - rot
        var BLP = fwd - str + rot

        var max = if (FRP > maxPower) FRP else maxPower
        if (max < FRP) max = FRP
        if (max < BLP) max = BLP
        if (max < BRP) max = BRP
        if (max < FLP) max = FLP

        FLP /= max
        FRP /= max
        BLP /= max
        BRP /= max

        robot.chassis.setPower(FLP, FRP, BLP, BRP)
    }

    private fun harvester() {
        val P = (gamepad1.left_trigger * 0.7 - gamepad1.right_trigger) * IntakeSystem.Motors.MaxPower
        //        telemetry.addData("Power: ", P)
        //        telemetry.update()
        robot.harvester.setPower(P)
        val outtake = if (gamepad1.x) 0.0 else 1.0
        robot.outTake.outTakeServo.setPosition(outtake)
    }
}
