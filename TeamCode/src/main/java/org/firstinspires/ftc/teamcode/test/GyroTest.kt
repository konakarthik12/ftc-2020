package org.firstinspires.ftc.teamcode.test

import android.content.Context
import android.hardware.camera2.CameraManager
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "GyroTest")
class GyroTest : MOETeleOp(useSlam = true) {
    override fun mainLoop() {
        telemetry.addData(robot.gyro.angle)
        telemetry.addData(robot.slam.getRawTheta())
        telemetry.addData(robot.slam.getRawPose().contentToString())
    }

    override fun initOpMode() {
//        robot.slam.restart()
    }


}