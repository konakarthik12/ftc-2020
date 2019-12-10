package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlamOptions
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Units
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Autonomous
import org.firstinspires.ftc.teamcode.misc.Rectangle
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Point

data class AutonConfig(val skystoneCropRect: Rectangle,
                       val robotToFieldOffset: Double,
                       val positionOffsets: Point,
                       val topSkystonePosition: Point,
                       val afterSkystonePosition: Point,
                       val dumpSkystonePosition: Point,
                       val parkPosition: Point)

fun flipAboutFieldYAxis(p: Point): Point = Point(Units.FIELD_SIZE - p.x, p.y)

fun reflectAutonConfig(config: AutonConfig): AutonConfig {
    val (skystoneCropRect, robotToFieldOffset, positionOffsets, topSkystonePosition,
            afterSkystonePosition, dumpSkystonePosition, parkPosition) = config

    return AutonConfig(
            skystoneCropRect = skystoneCropRect,
            robotToFieldOffset = -robotToFieldOffset,
            positionOffsets = flipAboutFieldYAxis(positionOffsets),
            topSkystonePosition = flipAboutFieldYAxis(topSkystonePosition),
            afterSkystonePosition = flipAboutFieldYAxis(afterSkystonePosition),
            dumpSkystonePosition = flipAboutFieldYAxis(dumpSkystonePosition),
            parkPosition = flipAboutFieldYAxis(parkPosition)
    )
}

abstract class MOEAuton(val isLeft: Boolean = true) : MOEOpMode() {
    protected val config: AutonConfig = if (isLeft) Autonomous.Left else Autonomous.Right
    // The values are ordered from top to bottom.
    private val skystonePositions: ArrayList<Point> = config.topSkystonePosition.let { (x, y) ->
        arrayListOf(
                Point(x, y),
                Point(x, y - (1 * Units.SKYSTONE_LENGTH)),
                Point(x, y - (2 * Units.SKYSTONE_LENGTH)),
                Point(x, y - (3 * Units.SKYSTONE_LENGTH)),
                Point(x, y - (4 * Units.SKYSTONE_LENGTH)),
                Point(x, y - (5 * Units.SKYSTONE_LENGTH))
        )
    }

    protected val skystonePairs: ArrayList<Pair<Point, Point>>
        get() = skystonePositions.let { arrayListOf(Pair(it[0], it[3]), Pair(it[1], it[4]), Pair(it[2], it[5])) }

    final override fun moeInternalInit() {
        robot = MOEBot(this, useCamera = true, useSlam = true)
        robot.slam.setOptions(config.robotToFieldOffset, config.positionOffsets.x, config.positionOffsets.y)
    }

    final override fun moeInternalPostInit() {
        robot.gyro.init(true)
    }
}
