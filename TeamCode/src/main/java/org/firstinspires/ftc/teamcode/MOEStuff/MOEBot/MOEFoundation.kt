package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOEServoSystem
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.FoundationSystem.Servos.Configs

class MOEFoundation {
    val foundationServoSystem = MOEServoSystem(Configs.FoundationServo1, Configs.FoundationServo2)

    fun moveDown() {
        foundationServoSystem.setPosition(1.0)
    }

    fun moveUp() {
        foundationServoSystem.setPosition(0.0)
    }
}
