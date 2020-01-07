package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig

import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle

data class AutonPositionConfig(val skystoneCropRect: Rectangle,
                               val robotToFieldOffset: Double,
                               val positionOffsets: Point,
                               val topSkystonePosition: Point,
                               val afterSkystonePosition: Point,
                               val dumpSkystonePosition: Point,
                               val parkPosition: Point)

fun flipAboutFieldYAxis(p: Point): Point = Point(MOEConstants.Units.FIELD_SIZE - p.x, p.y)

fun reflectAutonConfig(config: AutonPositionConfig): AutonPositionConfig {
    val (skystoneCropRect, robotToFieldOffset, positionOffsets, topSkystonePosition,
            afterSkystonePosition, dumpSkystonePosition, parkPosition) = config

    return AutonPositionConfig(
            skystoneCropRect = skystoneCropRect,
            robotToFieldOffset = -robotToFieldOffset,
            positionOffsets = flipAboutFieldYAxis(positionOffsets),
            topSkystonePosition = flipAboutFieldYAxis(topSkystonePosition),
            afterSkystonePosition = flipAboutFieldYAxis(afterSkystonePosition),
            dumpSkystonePosition = flipAboutFieldYAxis(dumpSkystonePosition),
            parkPosition = flipAboutFieldYAxis(parkPosition)
    )
}