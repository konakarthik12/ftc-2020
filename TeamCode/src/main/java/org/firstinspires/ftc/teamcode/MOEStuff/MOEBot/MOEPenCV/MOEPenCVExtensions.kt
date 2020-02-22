package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV

import android.graphics.Bitmap
import android.util.Log
import org.opencv.android.Utils
import org.opencv.core.CvException
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

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