package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEAutonConfigImpl


abstract class MOEAuton : MOERegularOpMode(), MOEAutonConfigImpl {
    //    protected val config: AutonConfig = if (isLeft) MOEAutonConstants.Left else MOEAutonConstants.Right
    // The values are ordered from top to bottom.
//   protected var
//    private lateinit var skystonePositions: List<Point>

    override fun moeInternalInit() {

//        skystonePositions = getAutonConfig().positionConfig.topSkystonePosition.let { (x, y) ->
//            List(6) {
//                Point(x, y - (it * Units.SKYSTONE_LENGTH))
//            }
//        }
    }

//    protected val skystonePairs: List<Pair<Point, Point>>
//        get() = skystonePositions.let { listOf(Pair(it[0], it[3]), Pair(it[1], it[4]), Pair(it[2], it[5])) }


    final override fun moeInternalPostInit() {
        if (robot.robotConfig.useGyro)
            robot.gyro.init(true)
    }
}
