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

        return robot.vuforia.getBitmap()
    }

    fun getCroppedBitmap(cropRect: Rectangle): Bitmap? {
        return getBitmap()?.crop(cropRect)
    }
}