package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEAutonArm

import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants

class MOEAutonArms {
    val left = MOEAutonArm(MOEHardwareConstants.AutonArms.Configs.LeftConfig)

    val right = MOEAutonArm(MOEHardwareConstants.AutonArms.Configs.RightConfig)
    val arms = listOf(left, right)
    fun openClaws() {
        arms.forEach { it.openClaw() }
    }

    fun closeClaws() {
        arms.forEach { it.closeClaw() }
    }

    fun initAutonArms() {
        arms.forEach { it.initAutonArm() }
    }
}