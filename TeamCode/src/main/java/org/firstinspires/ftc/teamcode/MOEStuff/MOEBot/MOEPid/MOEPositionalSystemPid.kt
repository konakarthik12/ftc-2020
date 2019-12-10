package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.Powers

class MOEPositionalSystemPid(Px: Double = 0.0, Ix: Double = 0.0, Dx: Double = 0.0, Fx: Double = 0.0,
                             Py: Double = 0.0, Iy: Double = 0.0, Dy: Double = 0.0, Fy: Double = 0.0,
                             Pt: Double = 0.0, It: Double = 0.0, Dt: Double = 0.0, Ft: Double = 0.0) {
    val xPid: MOEPositionalPid = MOEPositionalPid(Px, Ix, Dx, Fx)
    val yPid: MOEPositionalPid = MOEPositionalPid(Py, Iy, Dy, Fy)
    val tPid: MOETurnPid = MOETurnPid(Pt, It, Dt, Ft)

    constructor(xOptions: MOEPidValues, yOptions: MOEPidValues, turnOptions: MOEPidValues) :
            this(xOptions.P, xOptions.I, xOptions.D, xOptions.F,
                 yOptions.P, yOptions.I, yOptions.D, yOptions.F,
                 turnOptions.P, turnOptions.I, turnOptions.D, turnOptions.F)

    fun setSetpoints(x: Double, y: Double, theta: Double) {
        xPid.setpoint = x
        yPid.setpoint = y
        tPid.setpoint = theta
    }

    fun setOutputLimits(x: Double, y: Double, theta: Double) {
        xPid.setOutputLimits(x)
        yPid.setOutputLimits(y)
        tPid.setOutputLimits(theta)
    }

    fun getOutput(actualX: Double, actualY: Double, actualTheta: Double): Double {
        val xOutput = xPid.getOutput(actualX)
        val yOutput = yPid.getOutput(actualY)
        val tOutput = tPid.getOutput(actualTheta)

        return 0.0
    }

    fun getPowers(actualX: Double, actualY: Double, actualTheta: Double): Powers {
        val xOutput = xPid.getOutput(actualX)
        val yOutput = yPid.getOutput(actualY)
        val tOutput = tPid.getOutput(actualTheta)

        return Powers(0.0, 0.0, 0.0, 0.0)
    }
}
