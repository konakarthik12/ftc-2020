package org.firstinspires.ftc.teamcode.constants

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPidOptions
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPositionalPidOptions

class MOEPidConstants {
    object TurnPid {
        const val P = 0.01
    }

    object PositionalPid {
        val yOptions = MOEPidOptions(P = 0.025, I = 0.005, D = 0.35)
        val xOptions = MOEPidOptions(P = 0.06, D = 0.2)
        val tOptions = MOEPidOptions(P = 0.017, I = 0.005, D = 0.035)
        val DefaultOptions = MOEPositionalPidOptions(xOptions, yOptions, tOptions)
    }
}