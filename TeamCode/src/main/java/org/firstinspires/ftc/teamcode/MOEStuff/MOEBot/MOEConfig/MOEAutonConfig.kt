package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig

import org.firstinspires.ftc.teamcode.autonomous.constants.AutonConstants

data class MOEAutonConfig(val isLeft: Boolean = true) {
    val positionConfig = if (isLeft) AutonConstants.Locations.Left else AutonConstants.Locations.Right
}

interface MOEAutonConfigImpl {
    fun getAutonConfig() = MOEAutonConfig()
}