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
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap


fun getVuforiaParameters(): VuforiaLocalizer.Parameters {
    val params = VuforiaLocalizer.Parameters()
    params.vuforiaLicenseKey = MOEConstants.Vuforia.LICENSE_KEY
    params.cameraName = hardwareMap.get(WebcamName::class.java, "Webcam 1")
    return params
}


class MOEVuforia() {
    var vuforia: VuforiaLocalizer
    val cameraMonitorViewId: Int = hardwareMap.appContext.resources
            .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.packageName)
    private val parameters: VuforiaLocalizer.Parameters = getVuforiaParameters()

    init {
        setVuforiaSettings()
        vuforia = ClassFactory.getInstance().createVuforia(parameters)
        vuforia.enableConvertFrameToBitmap()
        vuforia.frameQueueCapacity = 1
    }

    private fun setVuforiaSettings() {
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGBA8888, true)
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