package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEDometryWheel
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants

@TeleOp(name = "EncoderDriveTest")
class EncoderDriveTest : MOETeleOp() {
    lateinit var device: MOEDometryWheel
    override fun initOpMode() {
        gamepad1.setJoystickDeadzone(0.0f)
        device = MOEDometryWheel(MOEHardwareConstants.Odometry.RightForward)
    }


    override fun mainLoop() {
        telemetry.addData("encode", device.getRawValue())
        telemetry.update()
    }


}