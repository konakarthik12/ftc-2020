package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV

import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvPipeline

class MOEPipeline(private val instance: MOEPenCV) : OpenCvPipeline() {
    private val redLine = Scalar(0.0, 255.0, 0.0)
    var lastFrame: Mat? = null
    var frameRequested = false
    override fun processFrame(input: Mat): Mat {
        if (frameRequested) {
            lastFrame = input
            frameRequested = false
        }
        val subMatrix = input.submat(instance.config.autonConfig.cropRectangle)
        val newMat = Mat()
        Imgproc.resize(subMatrix, newMat, Size(600.0, 200.0))

        if (instance.config.drawOverlay) {
            drawLines(newMat)
        }
        return newMat
    }

    private fun drawLines(newMat: Mat) {
        val width = newMat.width().toDouble()
        val height = newMat.height().toDouble()
        Imgproc.line(newMat, Point(width * (1 / 4.0), 0.0), Point(width * (1 / 4.0), height), redLine, 4)
//        Imgproc.line(newMat, Point(width * (2 / 4.0), 0.0), Point(width * (2 / 4.0), height), redLine, 4)
        Imgproc.line(newMat, Point(width * (3 / 4.0), 0.0), Point(width * (3 / 4.0), height), redLine, 4)
    }

    fun requestFrame() {
        frameRequested = true

    }

}