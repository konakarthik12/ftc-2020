package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig

import org.firstinspires.ftc.teamcode.constants.MOEAutonConstants

data class MOEAutonConfig(val isLeft: Boolean = true,
                          val useCamera: Boolean = true,
                          val useSlam: Boolean = true) {
    val positionConfig = if (isLeft) MOEAutonConstants.Left else MOEAutonConstants.Right

}

interface MOEAutonConfigImpl {
    fun getAutonConfig() = MOEAutonConfig()
}