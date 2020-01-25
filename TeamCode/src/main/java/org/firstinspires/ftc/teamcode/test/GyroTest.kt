package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@TeleOp(name = "GyroTest")
class GyroTest : MOETeleOp() {
    override fun mainLoop() {
        telemetry.addData(robot.gyro.angle)
//        telemetry.addData(robot.slam.getRawTheta())
//        telemetry.addData(robot.slam.getRawPose().toString())
    }

    override fun initOpMode() {
//        robot.slam.restart()
    }
}