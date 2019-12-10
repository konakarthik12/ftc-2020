package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.MOEConstants.FoundationSystem.Servos.Configs

class MOEFoundation {
    val foundationServo = MOEJointServo(Configs.FoundationServo1, Configs.FoundationServo2)

    fun closeServo() {
        foundationServo.setPosition(1.0)
    }

    fun openServo() {
        foundationServo.setPosition(0.0)
    }
}
