package org.firstinspires.ftc.teamcode.teleop

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.lerp
import kotlin.math.cos
import kotlin.math.sin

@TeleOp
open class CompTeleOp() : MOETeleOp() {
    //    val state: TeleOpState()
    override fun initOpMode() {
        addListeners()
        initLift()
        initOuttake()
    }

    private fun initLift() {
        robot.lift.resetEncoders()
        gpad2.dpad.down.onKeyDown {
            robot.lift.resetEncoders()

        }
        robot.lift.setTargetPosition(10)
        robot.lift.setRunToPosition()
        robot.lift.motors.forEach {
            it.mMotor.targetPositionTolerance = 25
        }
    }

    private fun addListeners() {
        gpad1.y.onKeyDown {
            robot.gyro.setToZero()
        }
    }

    private fun initOuttake() {
        robot.outtake.grabServo.setPosition(0.6)
        gpad2.left.bumper.onKeyDown {
            robot.outtake.outtakeServo.setPosition(0.0)
        }
        gpad2.right.bumper.onKeyDown {
            robot.outtake.outtakeServo.setPosition(1.0)
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
        outtake()
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
        val harvesterPow = (outPower - gamepad1.right_trigger)
        robot.harvester.setPower(harvesterPow)


    }

    private fun foundation() {
        robot.foundation.foundationServo.setPosition(if (gamepad1.left_bumper) 1.0 else 0.0)
    }

    var target = 0.0
    open fun lift() {
        if (gpad2.dpad.down()) {
            robot.lift.setRunWithoutEncoder()
            robot.lift.setPower(1.0)
        } else {
            robot.lift.setRunToPosition()
        }
        val right = gamepad2.right_trigger.toDouble()
//        val multi = if (target > 0) 3.0 else 0.0
        val left = gamepad2.left_trigger.toDouble() * 3.0
        robot.lift.motors.forEach {
            val error = it.error
            if (error > 0) {
                robot.lift.setPower(1.0)
            } else {
                robot.lift.setPower(-0.4)

            }
        }
        target += ((right - left) * 25)
        target = target.coerceAtLeast(0.0)
        robot.lift.setTargetPosition(target.toInt())
    }

    private fun outtake() {
        val outtake = when {
            gpad2.a.isToggled -> 1.0
            else -> 0.54
        }

        robot.outtake.grabServo.setPosition(outtake)
//        robot.outtake.outtakeServo.se
    }

}