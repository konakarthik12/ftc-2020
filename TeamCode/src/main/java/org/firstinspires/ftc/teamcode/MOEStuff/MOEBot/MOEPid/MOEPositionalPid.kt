package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEtion
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.telemetry
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import kotlin.math.cos
import kotlin.math.sin

data class MOEPositionalPidOptions(val xOptions: MOEPidOptions, val yOptions: MOEPidOptions, val turnOptions: MOEPidOptions)

class MOEPositionalSystemPid(val xPid: MOERawPid, val yPid: MOERawPid, val tPid: MOETurnPid) : MOEPidStructure<MOEtion, Powers> {
    override var input = { MOEtion(Point(0.0, 0.0), 0.0) }

    override var output: (pow: Powers) -> Unit = { }
    override var setpoint: () -> MOEtion = { MOEtion() }

    val pids = listOf(xPid, yPid, tPid)

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

        xPid.setpoint = {
            this.setpoint().pose.x
        }

        yPid.setpoint = {
            this.setpoint().pose.y
        }

        tPid.setpoint = {
            this.setpoint().degAng
        }
        xPid.threshold = 2.0
        yPid.threshold = 2.0
        tPid.threshold = 4.0
    }

    override var endCondition: (MOEtion) -> Boolean = {
        val b = (xPid.endCondition(it.pose.x) &&
                yPid.endCondition(it.pose.y)
                && tPid.endCondition(it.degAng))
//        Log.e("wholeEnd", b.toString())
        b

    }


    override fun getOutput(input: MOEtion, setpoint: MOEtion): Powers {
        val rawX = xPid.getOutput(input.pose.x, setpoint.pose.x)
        val rawY = yPid.getOutput(input.pose.y, setpoint.pose.y)
        val rawT = tPid.getOutput(input.degAng, setpoint.degAng)
        val angle = input.radAng
        val x = rawX * cos(angle) - rawY * sin(angle)
        val y = rawX * sin(angle) + rawY * cos(angle)
        telemetry.addData("pose", input)
        telemetry.addData("setpoint", setpoint)
        telemetry.addData("x", x)
        telemetry.addData("y", y)
        telemetry.addData("t", rawT)
        telemetry.update()

//        Log.e("x", x.toString())
//        Log.e("y", y.toString())
//        Log.e("t", rawT.toString())
        return Powers.fromMecanum(y, x, rawT)
    }

    fun reset() {
        pids.forEach { it.reset() }
    }

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