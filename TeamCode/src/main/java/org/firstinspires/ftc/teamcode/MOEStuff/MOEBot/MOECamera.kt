package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import android.graphics.Bitmap
import org.firstinspires.ftc.teamcode.constants.OpModeInterface
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Rectangle
import org.firstinspires.ftc.teamcode.utilities.crop
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot


class MOECamera(private val opMode: OpModeInterface) {
    //TODO: Change to camera
    fun getBitmap(): Bitmap? {

        return robot.vuforia.getBitmap()
    }

    fun getCroppedBitmap(cropRect: Rectangle): Bitmap? {
        return getBitmap()?.crop(cropRect)
    }
}