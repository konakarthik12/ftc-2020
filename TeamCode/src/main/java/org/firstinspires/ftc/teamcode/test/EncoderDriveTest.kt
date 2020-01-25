package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEDometryWheel
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp

@TeleOp(name = "EncoderDriveTest")
class EncoderDriveTest : CompTeleOp() {
    lateinit var strafeWheel: MOEDometryWheel
    lateinit var leftForward: MOEDometryWheel
    lateinit var rightForward: MOEDometryWheel
    override fun initOpMode() {
        super.initOpMode()
        strafeWheel = MOEDometryWheel(MOEHardwareConstants.Odometry.Strafe)
        leftForward = MOEDometryWheel(MOEHardwareConstants.Odometry.LeftForward)
        rightForward = MOEDometryWheel(MOEHardwareConstants.Odometry.RightForward)
    }


    override fun mainLoop() {
        super.mainLoop()
        telemetry.addData("imu", robot.gyro.angle)
        telemetry.addData("strafeWheel", strafeWheel.getValue())
        telemetry.addData("rightForward", rightForward.getValue())
        telemetry.addData("leftForward", leftForward.getValue())
        telemetry.update()
    }


}