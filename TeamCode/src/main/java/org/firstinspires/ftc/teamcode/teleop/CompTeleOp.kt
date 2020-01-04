package org.firstinspires.ftc.teamcode.teleop

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.lerp
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import java.time.Clock
import kotlin.math.cos
import kotlin.math.sin

@TeleOp
open class CompTeleOp() : MOETeleOp() {
    override fun initOpMode() {

        addListeners()
        //        Log.e("stuffe", "stuffe")

    }

    private fun addListeners() {
        gpad1.y.onKeyDown {
            robot.gyro.setToZero()
        }
    }

//    var oldTime = 0L
    override fun mainLoop() {
//        telemetry.addData(System.currentTimeMillis() - oldTime)
//        telemetry.update()
        chassis()
        harvester()
        foundation()
        lift()
        log()
//        oldTime = System.currentTimeMillis()


    }

    open fun log() {
        telemetry.addData("Running", this::class.simpleName)

    }

    val minPower = 0.6
    val maxPower = 1.0
    val powerRange = minPower..maxPower
    private fun chassis() {
        //        val bumperThrottle = 0.5
        val scaleX = 1
        val scaleY = 0.85
        val scaleRot = 0.75
        val angle = robot.gyro.eulerAngle
        var rawY = gpad1.left.stick.y()
        var rawX = gpad1.left.stick.x()
        var rot = gpad1.right.stick.x()

        val throttle = powerRange.lerp(gamepad1.left_trigger.toDouble())

        rawX *= scaleX * throttle
        rawY *= scaleY * throttle
        rot *= scaleRot * throttle

        val angRad = Math.toRadians(angle)
        val fwd = rawX * sin(angRad) + rawY * cos(angRad)
        val str = rawX * cos(angRad) - rawY * sin(angRad)
        //        telemetry.addData("angle", gpad1.left_stick_angle)
        //        telemetry.addData("magnitute", gpad1.left_stick_mag)

        val powers = Powers.fromRadAngle(gpad1.left.stick.angle
                + Math.toRadians(robot.gyro.angle), gpad1.left.stick.mag, rot)
//        telemetry.addData("powers", powers)
        robot.chassis.setPower(powers)
        //        robot.chassis.setPower(Powers.fromMecanum(fwd, str, rot, maxPower))
    }

    private fun harvester() {
        val outPower = if (gamepad1.b) 0.5 else 0.0
        val P = (outPower - gamepad1.right_trigger) * MOEConstants.IntakeSystem.Motors.MaxPower
        //        telemetry.addData("Power: ", P)
        //        telemetry.update()
        robot.harvester.setPower(P)
        //
        //        if (gamepad1.right_bumper && !rightBumperPressed){
        //            rightBumperPressed = true
        //            outtakeOpen = !outtakeOpen
        //        }
        //        if (rightBumperPressed && !gamepad1.right_bumper){
        //            rightBumperPressed = false
        //        }

        val outtake = when {
            gpad1.x() -> 0.0
            gpad1.right.bumper.isToggled -> 1.0
            else -> 0.6
        }

        robot.outTake.grabServo.setPosition(outtake)
    }

    private fun foundation() {
        robot.foundation.foundationServo.setPosition(if (gamepad1.left_bumper) 1.0 else 0.0)
    }

    open fun lift() {
        robot.lift.setPower(gpad2.trigger_diff)
    }

}