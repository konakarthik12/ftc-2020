package org.firstinspires.ftc.teamcode.constants

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPidOptions
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPositionalPidOptions

object MOEPidConstants {


    val yOptions = MOEPidOptions(P = 0.025, I = 0.005, D = 0.35)
    val xOptions = MOEPidOptions(P = 0.06, D = 0.2)
    val tOptions = MOEPidOptions(P = 0.045, I = 0.0007, D = 0.025)

    object PositionalPid {
        val DefaultOptions = MOEPositionalPidOptions(xOptions, yOptions, tOptions)

    }
}