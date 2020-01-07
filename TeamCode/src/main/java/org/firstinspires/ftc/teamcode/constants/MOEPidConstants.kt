package org.firstinspires.ftc.teamcode.constants

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPidOptions
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPositionalPidOptions

class MOEPidConstants {
    object TurnPid {
        const val P = 0.01
    }

    object PositionalPid {
        val xOptions = MOEPidOptions()
        val yOptions = MOEPidOptions()
        val tOptions = MOEPidOptions()
        val DefaultOptions = MOEPositionalPidOptions(xOptions, yOptions, tOptions)
    }
}