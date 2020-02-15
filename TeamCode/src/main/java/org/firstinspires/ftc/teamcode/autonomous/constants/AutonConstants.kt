package org.firstinspires.ftc.teamcode.autonomous.constants

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.AutonPositionConfig
import org.firstinspires.ftc.teamcode.autonomous.vision.HSVFilter
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle

object AutonConstants {
    object Locations {
        val Left = AutonPositionConfig(
                positionOffsets = Point(0.0, 0.0), // Point(15.0, 96.0),
                afterSkystonePosition = Point(48.0, 96.0),
                dumpSkystonePosition = Point(48.0, 192.0),
                parkPosition = Point(63.0, 144.0)
        )
        val Right = Left.reflect()
    }

    object Skystone {
        val TOP_SKYSTONE_POS = Point(96.0 + 4.0, 96.0 - 8.0)
        const val SKYSTONE_WIDTH = 4.0 * 2.0
        const val SKYSTONE_LENGTH = 8.0 * 2.0
        const val NUM_STONES = 6
        // Top-right and bottom-left points: (775, 234) | (322, 271)
        val SKYSTONE_CROP = Rectangle(322, 234, 453, 37)
        // is the stone a yellow stone
        val isYellowStone: HSVFilter = { (h, s, v) ->
            h in 30F..60F && s > 0.4 && v > 0.6
        }
    }


    const val FIELD_SIZE = 288.0
}