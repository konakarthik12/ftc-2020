package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point

data class MOEPositionalPidOptions(val xOptions: MOEPidOptions, val yOptions: MOEPidOptions, val turnOptions: MOEPidOptions)

class MOEPositionalSystemPid(val xPid: MOERawPid, val yPid: MOERawPid, val tPid: MOETurnPid) : MOEPidSystem(xPid, yPid, tPid) {
    init {
        xPid.input = {
            this.input().pose.x
        }

        yPid.input = {
            this.input().pose.y
        }

        tPid.input = {
            this.input().degAng
        }

    }

    fun run(sync: Boolean = true): Job {
        return GlobalScope.launch {
            while (ReferenceHolder.moeOpMode.iOpModeIsActive()) {
                val input1 = input()
                val output1 = getOutput(input1)
                output(output1)
                if (endCondition()) {
                    break
                }
            }
        }.also {
            if (sync) {
                runBlocking { it.join() }
            }
        }
    }

    private fun endCondition(): Boolean {
        return pids.all { it.isFinished() }
    }

    private fun getOutput(input: Transformation): Powers {
        val x = xPid.getOutput()
        val y = yPid.getOutput()
        val t = tPid.getOutput()
        return Powers.fromMecanum(y, x, t)
    }

    var input: () -> Transformation = { Transformation(Point(0.0, 0.0), 0.0) }


//    fun input(inFunc: () -> Transformation) {
//        xPid.input = {
//            inFunc().pose.x
//        }
//        yPid.input = {
//            inFunc().pose.y
//        }
//        tPid.input = {
//            inFunc().angle
//        }
//    }

    var output: (pow: Powers) -> Unit = { _: Powers -> }

    constructor(Px: Double = 0.0, Ix: Double = 0.0, Dx: Double = 0.0, Fx: Double = 0.0,
                Py: Double = 0.0, Iy: Double = 0.0, Dy: Double = 0.0, Fy: Double = 0.0,
                Pt: Double = 0.0, It: Double = 0.0, Dt: Double = 0.0, Ft: Double = 0.0) :
            this(MOERawPid(Px, Ix, Dx, Fx), MOERawPid(Py, Iy, Dy, Fy), MOETurnPid(Pt, It, Dt, Ft))

    constructor(xOptions: MOEPidOptions, yOptions: MOEPidOptions, turnOptions: MOEPidOptions) :
            this(MOERawPid(xOptions), MOERawPid(yOptions), MOETurnPid(turnOptions))

    constructor(options: MOEPositionalPidOptions) : this(
            options.xOptions,
            options.yOptions,
            options.turnOptions)
    //    val pids = listOf(xPid,yPid,tPid)

}