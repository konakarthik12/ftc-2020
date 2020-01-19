package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.*
import org.firstinspires.ftc.teamcode.constants.MOESlamConstants

interface MOEBotConstants {
    fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return MOEBotSubSystemConfig()
    }

    fun createRobot(): MOEBot {
        return MOEBot(this)
    }

    fun getSlamConfig(): MOESlamConfig {
        return MOESlamConstants.DefaultValues
    }

    fun getRobotInitialState(): MOERobotInitialStateConfig {
        return MOERobotInitialStateConfig()
    }

    fun getGyroConfig(): MOEGyroConfig {
        return MOEGyroConfig(getRobotInitialState().robotInitial.degAng)
    }


}
