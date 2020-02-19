package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.*
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV.MOEOpenCVConfig

interface MOEBotConstantsImpl {
    fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return MOEBotSubSystemConfig()
    }

    fun createRobot(): MOEBot {
        return MOEBot(this)
    }


    fun getRobotInitialState(): MOERobotInitialStateConfig {
        return MOERobotInitialStateConfig()
    }

    fun getGyroConfig(): MOEGyroConfig {
        return MOEGyroConfig(getRobotInitialState().robotInitial.degAng)
    }

    fun getVuforiaConfig(): MOEVuforiaConfig {
        return MOEVuforiaConfig()
    }

    fun getOpenCVConfig(): MOEOpenCVConfig {
        return MOEOpenCVConfig()
    }


}
