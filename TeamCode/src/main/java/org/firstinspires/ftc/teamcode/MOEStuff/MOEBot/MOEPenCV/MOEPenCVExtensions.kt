package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV

import android.graphics.Bitmap
import android.util.Log
import org.opencv.android.Utils
import org.opencv.core.CvException
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraRotation

fun Mat.toBitMap(): Bitmap? {
    var bmp: Bitmap? = null
    val rgb = Mat()
    Imgproc.cvtColor(this, rgb, Imgproc.COLOR_BGR2RGB)
    try {
        bmp = Bitmap.createBitmap(rgb.cols(), rgb.rows(), Bitmap.Config.ARGB_8888)
        Utils.matToBitmap(rgb, bmp)
    } catch (e: CvException) {
        Log.d("Exception", e.message)
    }
    return bmp
}

fun OpenCvCamera.startStreaming(res: Resolution, rotation: OpenCvCameraRotation) {
    startStreaming(res.width, res.height, rotation)
}

fun Mat.drawText(x: Double, y: Double, text: String, fontSize: Double = 1.0, color: Scalar = MOEPenCVConstants.BLACK) {
    Imgproc.putText(this, text, Point(x, y), 0, fontSize, color)

}