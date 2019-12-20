package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "EncoderDriveTest")
class EncoderDriveTest : MOETeleOp(useGyro = false) {
    override fun initOpMode() {
        gamepad1.setJoystickDeadzone(0.0f)
    }

    override fun mainLoop() {
        val mechanumToPowers = Powers.mechanumToPowers(
                gpad1.left_stick_y,
                gpad1.left_stick_x,
                gpad1.right_stick_x)
        mechanumToPowers *= 20.0
        telemetry.addData(mechanumToPowers)
        telemetry.addData(gpad1.left_stick_y)
        telemetry.addData(gpad1.left_stick_x)
        telemetry.addData(gpad1.right_stick_x)
        telemetry.update()
        robot.chassis.setVelocity(mechanumToPowers)
    }


}