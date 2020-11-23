package org.firstinspires.ftc.teamcode.teleop

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toRadians
import org.firstinspires.ftc.teamcode.utilities.external.toFixed

@TeleOp
open class UltimateGoalTeleOp : MOETeleOp() {
    override fun initOpMode() {
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE)
        addListeners()
        joystickDrive()
        log()
    }

    private fun joystickDrive() {

        robot.chassis.loop {
            val polar = gpad1.left.stick.vector()
            polar.rotate(-robot.gyro.angle)
            it.setFromPolar(polar, gpad1.right.stick.x())
        }

//        val scaleX = 1.0
//        val scaleY = 1.0
//        val scaleRot = 0.75
//
//        val angle = robot.gyro.angle
//
//        var rawY = gpad1.left.stick.y()
//        var rawX = gpad1.left.stick.x()
//        var rot = gpad1.right.stick.x()
//
//        var throttle = powerRange.lerp(gpad1.left.trigger())
//        if (gpad1.right.trigger() > 0.1) throttle = 0.4
//        rawX *= scaleX * throttle
//        rawY *= scaleY * throttle
//        rot *= scaleRot * throttle
//
//        if (gpad1.left.bumper() && abs(rot) > 0.1) {
//            rawY = rawY.coerceAtLeast(0.0)
//        }
//
//        val angRad = Math.toRadians(angle)
//        val fwd = rawX * sin(angRad) + rawY * cos(angRad)
//        val str = rawX * cos(angRad) - rawY * sin(angRad)
//
//        robot.chassis.setPower(Powers.fromMechanum(fwd, str, rot, maxPower))
    }


    private fun addListeners() {
        gpad1.y.onKeyDown {
            robot.gyro.setTo(90.0.toRadians())
        }
    }



    open fun log() {
        telemetry.addData("Running", this::class.simpleName)
        val powers = this.robot.chassis.getPowers()
        telemetry.addData("powers", powers.map { it.toFixed(3) })
        val up = "\u2191"
        val middle = " "
        val down = "\u2193"
        val arrows = powers.map { if (it > 0.0) up else if (it < 0.0) down else middle }
        telemetry.addData("front", "${arrows[0]}\t${arrows[1]}")
        telemetry.addData("backs", "${arrows[2]}\t${arrows[3]}")
    }


}