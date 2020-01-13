package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEGyroConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig

interface                                                                                                                                                       MOEBotConstants {
    fun getRobotConfig(): MOEBotConfig {
        return MOEBotConfig()
    }

    fun getInitialGyro() = 0.0
    fun createRobot(): MOEBot {
        return MOEBot(this)
    }

    fun getSlamConfig(): MOESlamConfig {
        return MOESlamConfig()
    }

    fun getGyroConfig(): MOEGyroConfig {
        return MOEGyroConfig()
    }


}
