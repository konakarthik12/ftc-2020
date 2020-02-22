package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV

import android.graphics.Bitmap
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation

class MOEPenCV(val config: MOEOpenCVConfig) {
    lateinit var webcam: OpenCvCamera
    val pipeline = MOEPipeline(this)

    init {
        val get = ReferenceHolder.hardwareMap.get(WebcamName::class.java, "Webcam 1")
        val instance = OpenCvCameraFactory.getInstance()
        webcam = if (config.enablePreview) instance.createWebcam(get, getCameraMonitorView()) else instance.createWebcam(get)
        webcam.setPipeline(pipeline)
        webcam.openCameraDevice()
        webcam.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT)
    }

    private fun getCameraMonitorView() =
            ReferenceHolder.appContext.resources.getIdentifier("cameraMonitorViewId", "id", ReferenceHolder.hardwareMap.appContext.packageName)

    fun stop() {
        webcam.stopStreaming()
    }

    fun getBitmap(): Bitmap? {
        pipeline.requestFrame()
        while (ReferenceHolder.moeOpMode.iOpModeIsActive() && pipeline.lastFrame == null) {
        }
        return pipeline.lastFrame!!.toBitMap()!!
    }
}