package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.closestAngleDifference

class MOETurnPid(P: Double = 0.0,
                 I: Double = 0.0,
                 D: Double = 0.0,
                 F: Double = 0.0) : MOEPid(P, I, D, F) {

    constructor(options: MOEPidValues) : this(options.P, options.I, options.D, options.F)

    override fun getError(setPoint: Double, actual: Double): Double {
        return actual.closestAngleDifference(setPoint)
    }
}