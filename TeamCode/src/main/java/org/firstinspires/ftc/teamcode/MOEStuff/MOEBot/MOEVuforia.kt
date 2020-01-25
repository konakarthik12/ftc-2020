package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import android.graphics.Bitmap
import com.vuforia.PIXEL_FORMAT
import com.vuforia.Vuforia
import org.firstinspires.ftc.robotcore.external.ClassFactory
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.MOEVuforiaConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap





class MOEVuforia() {
    lateinit var vuforia: VuforiaLocalizer
    private val parameters: VuforiaLocalizer.Parameters = MOEVuforiaConstants.params

    init {
        setVuforiaSettings()
    }

    private fun setVuforiaSettings() {
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGBA8888, true)
        vuforia = ClassFactory.getInstance().createVuforia(parameters)
        vuforia.enableConvertFrameToBitmap()
        vuforia.frameQueueCapacity = 1
    }

    fun getBitmap(): Bitmap? {
        val frame: VuforiaLocalizer.CloseableFrame

        try {
            frame = vuforia.frameQueue.take()
        } catch (e: InterruptedException) {
            return null
        }

        return vuforia.convertFrameToBitmap(frame)
    }
}
