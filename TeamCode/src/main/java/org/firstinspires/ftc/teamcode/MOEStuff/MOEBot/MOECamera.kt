package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import android.graphics.Bitmap
import com.vuforia.PIXEL_FORMAT
import com.vuforia.Vuforia
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.constants.OpModeInterface
import org.firstinspires.ftc.teamcode.misc.Rectangle
import org.firstinspires.ftc.teamcode.utilities.crop
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot


class MOECamera(private val opMode: OpModeInterface) {
    //TODO: Change to camera
    fun getBitmap(): Bitmap? {
//        val options = BitmapFactory.Options()
//
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888
////        opMode.telemetry.addData("external", Environment.getExternalStorageDirectory().toString())
//        val f = File(Environment.getExternalStorageDirectory().toString() + "/FirstTest/3.jpg")
//        if (!f.exists()) {
//            opMode.iTelemetry.addData("File not found")
//            opMode.iTelemetry.addData(f.toString())
//            opMode.iTelemetry.update()
//        }
//        opMode.iTelemetry.update()
//        var bitmap: Bitmap? = null
//        try {
//            bitmap = BitmapFactory.decodeStream(FileInputStream(f), null, options)
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        }
//        return bitmap
        return robot.vuforia.getBitmap()
    }

    fun getCroppedBitmap(cropRect: Rectangle): Bitmap? {
        return getBitmap()?.crop(cropRect)
    }
}