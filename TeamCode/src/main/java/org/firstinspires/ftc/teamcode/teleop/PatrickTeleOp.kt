package org.firstinspires.ftc.teamcode.teleop

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.lerp
import kotlin.math.cos
import kotlin.math.sin

@TeleOp
open class PatrickTeleOp : MOETeleOp() {
//    val sd_main = File(Environment.getExternalStorageDirectory().absolutePath + "/comp_odometry.txt")
//    val writer = sd_main.printWriter()

    //    val state: TeleOpState()
    override fun initOpMode() {
        addListeners()
        initLift()
        initOuttake()
        robot.foundation.moveUp()
//        robot.odometry.launchLoop()
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
    }

    //    var oldTime = 0L
    override fun mainLoop() {
        joystickChassis()
        dpadChassis()
        intake()
        foundation()
        lift()
        outtake()
//        autonArms()
        log()
//        telemetry.addData("timed", System.currentTimeMillis() - oldTime)
    }

    private fun dpadChassis() {
        val scale = 0.3
        val angle = gpad1.dpad.angle() ?: return
        val rot = gpad1.right.stick.x()
        robot.chassis.setPower(Powers.fromAng(angle, scale, rot))
    }

    private fun autonArms() {
//        robot.autonArms.left.apply {
//            if (gpad1.dpad.left.isToggled) lowerArm() else raiseArm()
//        }
//        robot.autonArms.right.apply {
//            if (gpad1.dpad.right.isToggled) lowerArm() else raiseArm()
//        }
//        robot.autonArms.apply {
//            if (gpad1.a.isToggled) closeClaws() else this.openClaws()
//        }

    }

    open fun log() {
        telemetry.addData("Runninge", this::class.simpleName)
//        telemetry.addData("limswitch", robot.lift.limitSwitch.isPressed)
        telemetry.addData("lift", target)
        telemetry.addData("acutal", robot.lift.getPositions().average())
        telemetry.addData("lastHighest", lastHighest)
        telemetry.addData("lastHighestTol", (robot.lift.getPositions().average() + heightTol))

        telemetry.addData("switch", robot.lift.limitSwitch.isPressed)
//        val motion = robot.odometry.astarMoetion()
//        telemetry.addData("pose", motion.pose)
//        telemetry.addData("degs", motion.degAng)
//        writer.println("$motion.x\t$motion.y\t$motion.degAng")
    }

    val minPower = 0.4
    val maxPower = 7.0
    val powerRange = minPower..maxPower
    val scaleX = 1
    val scaleY = 0.85
    val scaleRot = 0.75
    open fun joystickChassis() {
        //        val bumperThrottle = 0.5

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

        robot.chassis.setPower(Powers.fromMechanum(fwd, str, rot, maxPower))
//        val powers = Powers.fromRadAngle(gpad1.left.stick.angle
//                + Math.toRadians(robot.gyro.angle), gpad1.left.stick.mag, rot)
//        telemetry.addData("powers", powers)
//        robot.chassis.setPower(powers)
    }

    private fun intake() {
        val outPower = if (gpad1.b()) 0.25 else 0.0
        val harvesterPow = (outPower - gpad1.right.trigger())
        robot.intake.setPower(harvesterPow)
    }

    private fun foundation() {
        robot.foundation.apply {
            if (gpad1.left.bumper()) this.moveDown() else this.moveUp()
        }
    }

    var target = 0.0
    val initialHeight = 600.0
    val stoneHeight = 600.0
    var lastHighest = 0.0
    val heightTol = 0.0
    var liftPower = 1.0
    val intakeHeight = 250

    var dpadPressed = false
    var aPressed = false


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

        /*robot.lift.motors.forEach {
            val power = if (it.targetIsHigherThanCurrent) {
                if (gpad2.left.trigger.button()) 1.0 else 0.7
            } else {
                if (gpad2.left.trigger.button()) 0.5 else 0.28
            }
            it.setPower(power)
        }*/


        var liftCurPos = robot.lift.getPositions().average()


        target += ((up + upSlow) * 45)
        if (gpad2.left.stick.y() > -0.1) {
            target = target.coerceAtLeast(0.0)
        }


        if (gpad2.dpad.up.isPressed) {
            if (!dpadPressed) {
                if (liftCurPos < initialHeight * .9) {
                    target = initialHeight
                    dpadPressed = true
                }
                if (liftCurPos > initialHeight * .9) {
                    target += stoneHeight
                    dpadPressed = true
                }

            }
        }

        if (gpad2.dpad.down.isPressed) {
            if (!dpadPressed) {
                target -= stoneHeight
                dpadPressed = true
            }
        }
        if ((!gpad2.dpad.down.isPressed && !gpad2.dpad.up.isPressed)) {
            dpadPressed = false
        }

        if (gpad2.a.isPressed) {

            if (!aPressed) {
                val getCurPos = liftCurPos + heightTol
                if (getCurPos >= lastHighest) {
                    aPressed = true
                    lastHighest = liftCurPos
                    target = 0.0
                } else {
                    aPressed = true
                    target = lastHighest
                }

            }
        }

        if (!gpad2.a.isPressed) {
            aPressed = false
        }
        if (gpad2.y.isPressed) {
            lastHighest = liftCurPos
        }

        if (liftCurPos > target) {
            liftPower = 1.0
        }
        if (liftCurPos < target) {
            liftPower = 1.0
        }
        robot.lift.setPower(liftPower)
        if (gpad1.right.trigger() > .1 && !gpad2.x.isPressed) {
            robot.lift.setTargetPosition(intakeHeight)
        } else {
            robot.lift.setTargetPosition(target.toInt())
        }

    }

    open fun outtake() {

        if (gpad2.b.isToggled) {
            robot.outtake.grab()
        } else {
            robot.outtake.release()
        }
        val outtakeCurPos = robot.outtake.outtakeServo.getPosition()
        val outtakeSpeed = .025

        if (gpad2.left.trigger() > .1 && outtakeCurPos > .05) {
            robot.outtake.outtakeServo.setPosition(outtakeCurPos - gpad2.left.trigger() * outtakeSpeed)
        }
        if (gpad2.right.trigger() > .1 && outtakeCurPos < .95) {
            robot.outtake.outtakeServo.setPosition(outtakeCurPos + gpad2.right.trigger() * outtakeSpeed)
        }
        if (gpad2.left.bumper.isPressed) {
            robot.outtake.moveIn()
        } else if (gpad2.right.bumper.isPressed) {
            robot.outtake.moveOut()
        }

    }


}