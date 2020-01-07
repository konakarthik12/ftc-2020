package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

class MOERawPid(P: Double = 0.0,
                I: Double = 0.0,
                D: Double = 0.0,
                F: Double = 0.0) : MOEFancyPid(P, I, D, F) {
    constructor(options: MOEPidOptions) : this(options.P, options.I, options.D, options.F)

    override fun getError(setPoint: Double, actual: Double): Double {
        return setPoint - actual
    }

}
