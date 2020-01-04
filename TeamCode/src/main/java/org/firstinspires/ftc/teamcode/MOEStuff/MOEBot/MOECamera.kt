package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import android.graphics.Bitmap
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle
import org.firstinspires.ftc.teamcode.utilities.internal.crop
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot


class MOECamera {
    fun getBitmap(): Bitmap? {

        return robot.vuforia.getBitmap()
    }

    fun getCroppedBitmap(cropRect: Rectangle): Bitmap? {
        return getBitmap()?.crop(cropRect)
    }
}