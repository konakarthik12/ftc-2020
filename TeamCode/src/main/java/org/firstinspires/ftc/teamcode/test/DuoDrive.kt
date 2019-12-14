package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.utilities.addData
import kotlin.math.cos
import kotlin.math.sin

@TeleOp(name = "DuoDrive")
open class DuoDrive : MOETeleOp() {
    //    var outtakeOpen = true
    //    var rightBumperPressed = false

    override fun initOpMode() {
        addListeners()
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")

    }

    private fun addListeners() {
        gpad1.y.onKeyDown {
            //            ayyy, it works
            robot.gyro.resetEulerAngle()
        }
    }

    override fun mainLoop() {
        harvester()
        foundation()
        chassis()
        other()

    }

    private fun other() {
        if (gamepad1.y) {
            robot.gyro.resetEulerAngle()
        }
    }

    private fun foundation() {
        robot.foundation.foundationServo.setPosition(if (gamepad1.left_bumper) 1.0 else 0.0)
        //        robot.foundation.foundationServo.servo1.setPosition(if (gamepad1.left_bumper) 1.0 else 0.0)
        //        robot.foundation.foundationServo.servo.setPosition(if (gamepad1.left_bumper) 1.0 else 0.0)
    }

    private fun chassis() {
        val bumperThrottle = 0.5
        val maxPower = 1.0
        val minPower = 0.6
        val scaleX = 1
        val scaleY = 0.85
        val scaleRot = 0.75
        val angle = robot.gyro.eulerAngle
        var rawX = -gamepad1.left_stick_x.toDouble()
        var rawY = (gamepad1.left_stick_y).toDouble()
        var rot = gamepad1.right_stick_x.toDouble()

        val throttle = (maxPower - minPower) * gamepad1.left_trigger + minPower
        //        if (gamepad1.right_bumper) {
        //            throttle *= bumperThrottle
        //            if (angle > 0) angle = -90.0
        //            if (angle > 0) angle = 90.0
        //        }

        rawX *= scaleX * throttle
        rawY *= scaleY * throttle
        rot *= scaleRot * throttle

        val fwd = rawX * sin(Math.toRadians(angle)) + rawY * cos(Math.toRadians(angle))
        val str = rawX * cos(Math.toRadians(angle)) - rawY * sin(Math.toRadians(angle))

        var FRP = fwd - str - rot
        var FLP = fwd + str + rot
        var BRP = fwd + str - rot
        var BLP = fwd - str + rot

        var max = if (FRP > maxPower) FRP else 1.0
        if (max < FRP) max = FRP
        if (max < BLP) max = BLP
        if (max < BRP) max = BRP
        if (max < FLP) max = FLP

        FLP /= max
        FRP /= max
        BLP /= max
        BRP /= max

        val i = 20
        robot.chassis.setVelocity(FLP * i, FRP * i, BLP * i, BRP * i)
    }

    private fun harvester() {
        val outPower = if (gamepad1.b) 0.5 else 0.0
        val P = (outPower - gamepad1.right_trigger) * MOEConstants.IntakeSystem.Motors.MaxPower
        //        telemetry.addData("Power: ", P)
        //        telemetry.update()
        robot.harvester.setPower(P)
        var outtake: Double = 0.0
        //
        //        if (gamepad1.right_bumper && !rightBumperPressed){
        //            rightBumperPressed = true
        //            outtakeOpen = !outtakeOpen
        //        }
        //        if (rightBumperPressed && !gamepad1.right_bumper){
        //            rightBumperPressed = false
        //        }

        if (gamepad1.x) {
            outtake = 0.0
        } else if (gpad1.right_bumper.isToggled) {
            outtake = 1.0
        } else {
            outtake = 0.6
        }

        robot.outTake.outTakeServo.setPosition(outtake)
    }
}
