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
        var mechanumToPowers = Powers.mechanumToPowers(
                gpad1.left_stick_y,
                gpad1.left_stick_x,
                gpad1.right_stick_x)
        if (gpad1.dpad_left.isPressed) {
            mechanumToPowers = Powers.mechanumToPowers(0.0, -1.0, 0.0)
        } else if (gpad1.dpad_right.isPressed) {
            mechanumToPowers = Powers.mechanumToPowers(0.0, 1.0, 0.0)
        } else if (gpad1.dpad_up.isPressed) {
            mechanumToPowers = Powers.mechanumToPowers(1.0, 0.0, 0.0)
        }
        else if (gpad1.dpad_down.isPressed) {
            mechanumToPowers = Powers.mechanumToPowers(-1.0, 0.0, 0.0)
        }

        mechanumToPowers *= 20.0
        telemetry.addData(mechanumToPowers)
        telemetry.addData(gpad1.left_stick_y)
        telemetry.addData(gpad1.left_stick_x)
        telemetry.addData(gpad1.right_stick_x)
        telemetry.update()
        robot.chassis.setVelocity(mechanumToPowers)
//        robot.chassis.setVelocity()
    }


}