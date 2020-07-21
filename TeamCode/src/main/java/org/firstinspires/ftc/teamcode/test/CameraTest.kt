package org.firstinspires.ftc.teamcode.test

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV.MOEOpenCVConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.autonomous.sideconfig.AutonSideConstants
import org.firstinspires.ftc.teamcode.autonomous.vision.getSkyStoneLocationFromBitmap

class CameraTest : MOEAuton() {
    override fun initOpMode() {
//        val systemService = hardwareMap.appContext.getSystemService(Context.CAMERA_SERVICE) as CameraManager
//        Log.e("stuff", systemService.cameraIdList.toString())
//        telemetry.addData(systemService.cameraIdList.contentToString())
//        telemetry.update()
//        Log.e("stuff", systemService.cameraIdList.toString())
    }

    override fun run() {
        val bitmap = robot.opencv.getBitmap()
        val location = getSkyStoneLocationFromBitmap(bitmap!!, AutonSideConstants.blue.cropRectangle)
        while (opModeIsActive()) {
            telemetry.addData("location", location)
            telemetry.update()
        }

    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply { useGyro = false;useOpenCV = true; }
    }

    override fun getOpenCVConfig(): MOEOpenCVConfig {
        return super.getOpenCVConfig().apply {
            //            resolution = Resolution(1280, 720)
            useInternalCamera = false
            processExtra = false
            drawOverlay = true
        }
    }
}