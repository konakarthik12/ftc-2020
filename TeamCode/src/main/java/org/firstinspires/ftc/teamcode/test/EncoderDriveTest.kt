package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@TeleOp(name = "EncoderDriveTest")
class EncoderDriveTest : MOETeleOp() {
    override fun initOpMode() {
        gamepad1.setJoystickDeadzone(0.0f)
    }

    override fun mainLoop() {
        var mechanumToPowers = Powers.fromMecanum(
                gpad1.left.stick.y(),
                gpad1.left.stick.x(),
                gpad1.right.stick.x())
        if (gpad1.dpad.left.isPressed) {
            mechanumToPowers = Powers.fromMecanum(0.0, -1.0, 0.0)
        } else if (gpad1.dpad.right.isPressed) {
            mechanumToPowers = Powers.fromMecanum(0.0, 1.0, 0.0)
        } else if (gpad1.dpad.up.isPressed) {
            mechanumToPowers = Powers.fromMecanum(1.0, 0.0, 0.0)
        } else if (gpad1.dpad.down.isPressed) {
            mechanumToPowers = Powers.fromMecanum(-1.0, 0.0, 0.0)
        }

        mechanumToPowers *= 20.0
        telemetry.addData(mechanumToPowers)
        telemetry.addData(gpad1.left.stick.y())
        telemetry.addData(gpad1.left.stick.x())
        telemetry.addData(gpad1.right.stick.x())
        telemetry.update()
        robot.chassis.setVelocity(mechanumToPowers)
        //        robot.chassis.setVelocity()
    }


}