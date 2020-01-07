package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEAutonConfigImpl
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Units
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point


abstract class MOEAuton : MOERegularOpMode(), MOEAutonConfigImpl {
    //    protected val config: AutonConfig = if (isLeft) MOEAutonConstants.Left else MOEAutonConstants.Right
    // The values are ordered from top to bottom.
//   protected var
    private lateinit var skystonePositions: ArrayList<Point>

    override fun moeInternalInit() {

        skystonePositions = getAutonConfig().positionConfig.topSkystonePosition.let { (x, y) ->
            arrayListOf(
                    Point(x, y),
                    Point(x, y - (1 * Units.SKYSTONE_LENGTH)),
                    Point(x, y - (2 * Units.SKYSTONE_LENGTH)),
                    Point(x, y - (3 * Units.SKYSTONE_LENGTH)),
                    Point(x, y - (4 * Units.SKYSTONE_LENGTH)),
                    Point(x, y - (5 * Units.SKYSTONE_LENGTH))
            )
        }
    }

    protected val skystonePairs: ArrayList<Pair<Point, Point>>
        get() = skystonePositions.let { arrayListOf(Pair(it[0], it[3]), Pair(it[1], it[4]), Pair(it[2], it[5])) }


    final override fun moeInternalPostInit() {
        robot.gyro.init(true)
    }

}
