package org.firstinspires.ftc.teamcode.autonomous.vision

import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import org.firstinspires.ftc.teamcode.autonomous.constants.AutonConstants
import org.firstinspires.ftc.teamcode.autonomous.constants.AutonConstants.Skystone.SKYSTONE_CROP
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle
import org.firstinspires.ftc.teamcode.utilities.internal.crop
import org.firstinspires.ftc.teamcode.utilities.internal.saveTo
import org.firstinspires.ftc.teamcode.utilities.internal.scale
import java.util.*
import kotlin.math.min

typealias HSVFilter = (HSV) -> Boolean

enum class SkyStoneLocation { LEFT, MIDDLE, RIGHT }

//1920 x 1080 | 800 x 448
fun getSkyStoneLocationFromBitmap(bm: Bitmap): SkyStoneLocation {
    val randomUUID = UUID.randomUUID()
    val file = Environment.getExternalStorageDirectory().absolutePath + "/FirstTest/skystone_$randomUUID.png"
    Log.e("imagePath", file)
    bm.saveTo(file)
    val frame = SKYSTONE_CROP
    val crop = bm.crop(frame)
    val cropped_file = Environment.getExternalStorageDirectory().absolutePath + "/FirstTest/skystone_${randomUUID}_cropped.png"
    crop.saveTo(cropped_file)
    val image = crop.scale(3, 1)!!
    // The top-right and bottom-left of the rect: (1885, 539), (715, 661).

    val leftV = image.getPixel(0, 0).toHSV().V
    val centerV = image.getPixel(1, 0).toHSV().V
    val rightV = image.getPixel(2, 0).toHSV().V

    val minV = min(min(leftV, centerV), rightV)

    return when {
        leftV == minV -> {
            SkyStoneLocation.LEFT
        }
        centerV == minV -> {
            SkyStoneLocation.MIDDLE
        }
        else -> {
            SkyStoneLocation.RIGHT
        }
    }
}

