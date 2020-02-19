package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOEServoSystem
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.FoundationSystem.Servos.Configs

class MOEFoundation {
    val foundationServoSystem = MOEServoSystem(Configs.LeftFoundationGrabber, Configs.RightFoundationGrabber)

    fun moveDown() {
        foundationServoSystem.setPosition(1.0)
    }

    fun moveUp() {
        foundationServoSystem.setPosition(0.0)
    }
}
