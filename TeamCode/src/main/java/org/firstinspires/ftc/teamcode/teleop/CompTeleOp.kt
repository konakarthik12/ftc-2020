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
        robot.lift.setTargetPosition(10)
        robot.lift.setRunToPosition()
        robot.lift.setTargetTolorence(25)
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
        intake()
        foundation()
        lift()
        outtake()
        log()
//        oldTime = System.currentTimeMillis()


    }

    open fun log() {
        telemetry.addData("Runninge", this::class.simpleName)
//        telemetry.addData("limswitch", robot.lift.limitSwitch.isPressed)
        telemetry.addData("lift", target)
        telemetry.addData("acutal", robot.lift.getPositions().average())
        telemetry.addData("switch", robot.lift.limitSwitch.isPressed)
    }

    val minPower = 0.6
    val maxPower = 1.0
    val powerRange = minPower..maxPower
    private fun chassis() {
        //        val bumperThrottle = 0.5
        val scaleX = 1
        val scaleY = 0.85
        val scaleRot = 0.75
        val angle = robot.gyro.angle

//        telemetry.addData("gyro", angle)

        var rawY = gpad1.left.stick.y()
        var rawX = gpad1.left.stick.x()
        var rot = gpad1.right.stick.x()

        val throttle = powerRange.lerp(gpad1.left.trigger())

        rawX *= scaleX * throttle
        rawY *= scaleY * throttle
        rot *= scaleRot * throttle

        val angRad = Math.toRadians(angle)
//        telemetry.addData()
        val fwd = rawX * sin(angRad) + rawY * cos(angRad)
        val str = rawX * cos(angRad) - rawY * sin(angRad)
        //        telemetry.addData("angle", gpad1.left_stick_angle)
        //        telemetry.addData("magnitute", gpad1.left_stick_mag)

        robot.chassis.setPower(Powers.fromMecanum(fwd, str, rot, maxPower))
//        val powers = Powers.fromRadAngle(gpad1.left.stick.angle
//                + Math.toRadians(robot.gyro.angle), gpad1.left.stick.mag, rot)
//        telemetry.addData("powers", powers)
//        robot.chassis.setPower(powers)
    }

    private fun intake() {
        val outPower = if (gpad1.b()) 0.25 else 0.0
        val harvesterPow = (outPower - gpad1.right.trigger())
        robot.harvester.setPower(harvesterPow)
    }

    private fun foundation() {
        robot.foundation.foundationServo.setPosition(if (gpad1.left.bumper()) 1.0 else 0.0)
    }

    var target = 0.0
    open fun lift() {
//        robot.lift.bottomOutIfNeeded()
//        if (gpad2.dpad.down()) {
//            robot.lift.setRunWithoutEncoder()
//            robot.lift.setPower(1.0)
//            return
//        } else {
//            robot.lift.setRunToPosition()
//        }

        val up = gpad2.left.stick.y()
//        val multi = if (target > 0) 3.0 else 0.0
//        val down = gpad2.left.stick.y() * -1
        val upSlow = gpad2.right.stick.y() * 0.5
//        val downSlow = gpad2.right.    stick.y() * -0.25
        robot.lift.motors.forEach {
            val error = it.error
            if (error > 0) {
                robot.lift.setPower(1.0)
            } else {
                robot.lift.setPower(-0.4)
            }
        }
        target += ((up + upSlow) * 35)
        if (gpad2.left.stick.y() > -0.1) {
            target = target.coerceAtLeast(0.0)
        }
//        target = target.coerceAtLeast(0.0)
        robot.lift.setTargetPosition(target.toInt())
    }

    private fun outtake() {
        val grabServo = when {
            gpad2.b.isToggled -> 1.0
            else -> 0.54
        }

        robot.outtake.grabServo.setPosition(grabServo)
//        robot.outtake.outtakeServo.se
    }
}