package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEDometryEncoder
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@TeleOp(name = "EncoderDriveTest")
class EncoderDriveTest : MOETeleOp() {
    lateinit var device: MOEDometryEncoder
    override fun initOpMode() {
        gamepad1.setJoystickDeadzone(0.0f)
        device = MOEDometryEncoder(MOEHardwareConstants.Odometry.RightForward)
    }


    override fun mainLoop() {
        telemetry.addData("encode", device.getValue())
        telemetry.update()
    }


}