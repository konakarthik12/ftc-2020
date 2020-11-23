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

    }


    private fun addListeners() {
        gpad1.y.onKeyDown {
            robot.gyro.setTo(90.0.toRadians())
        }
    }


    open fun log() {
        telemetry.addData("Running", this::class.simpleName)
    }


}