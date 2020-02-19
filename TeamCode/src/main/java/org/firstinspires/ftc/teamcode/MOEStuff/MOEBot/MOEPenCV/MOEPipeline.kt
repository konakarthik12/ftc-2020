package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV

import android.util.Log
import org.firstinspires.ftc.teamcode.autonomous.constants.AutonConstants
import org.opencv.core.*
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvPipeline

class MOEPipeline(val instance: MOEPenCV) : OpenCvPipeline() {
    private val redLine = Scalar(0.0, 255.0, 0.0)
    var lastFrame: Mat? = null
    override fun processFrame(input: Mat): Mat {

        val subMatrix = input.submat(AutonConstants.Skystone.SKYSTONE_CROP)
        val newMat = Mat()
        Imgproc.resize(subMatrix, newMat, Size(600.0, 200.0))
        val width = newMat.width()
//        Log.e("width", width.toString())
        if (instance.config.drawOverlay) {
            drawLines(newMat, width)
        }
        return newMat
//        return input
    }

    private fun drawLines(newMat: Mat, width: Int) {
        val height = newMat.height().toDouble()
        Imgproc.line(newMat, Point(width * (1 / 3.0), height), Point(width / 3.0, height), redLine, 4)
        Imgproc.line(newMat, Point(width * (2 / 3.0), height), Point(width / 3.0, height), redLine, 4)
    }

}