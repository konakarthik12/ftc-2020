package org.firstinspires.ftc.teamcode.test

import android.content.Context
import android.hardware.camera2.CameraManager
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@TeleOp(name = "CameraTest")
class CameraTest : MOEAuton() {
    override fun initOpMode() {
        val systemService = hardwareMap.appContext.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        Log.e("stuff", systemService.cameraIdList.toString())
        telemetry.addData(systemService.cameraIdList.contentToString())
        telemetry.update()
//        Log.e("stuff", systemService.cameraIdList.toString())
    }

    override fun run() {

    }

}