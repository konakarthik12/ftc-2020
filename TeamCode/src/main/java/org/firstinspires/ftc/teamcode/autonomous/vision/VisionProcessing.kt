package org.firstinspires.ftc.teamcode.autonomous.vision

import android.graphics.Bitmap
import android.os.Environment
import org.firstinspires.ftc.teamcode.autonomous.constants.AutonConstants
import org.firstinspires.ftc.teamcode.utilities.internal.saveTo
import org.firstinspires.ftc.teamcode.utilities.internal.scale
import java.util.*

typealias HSVFilter = (HSV) -> Boolean

enum class SkyStoneLocation { LEFT, MIDDLE, RIGHT }

fun getSkyStoneLocationFromBitmap(bm: Bitmap): SkyStoneLocation {
    bm.saveTo(Environment.getExternalStorageDirectory().absolutePath + "/FirstTest/skysone_${UUID.randomUUID()}.png")
    val image = bm.scale(2, 1)!!
    val leftIsYellow = image.getPixel(0, 0).isYellowStone()
    val rightIsYellow = image.getPixel(1, 0).isYellowStone()
// the one that isn't yellow is the one you are looking for
    return when {
        !leftIsYellow -> {
            SkyStoneLocation.LEFT
        }
        !rightIsYellow -> {
            SkyStoneLocation.MIDDLE
        }
        else -> {
            SkyStoneLocation.RIGHT
        }
    }
}

private fun Int.isYellowStone(): Boolean {
    return AutonConstants.Skystone.isYellowStone(this.toHSV())
}
