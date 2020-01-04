package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.ServoConfig

class MOEJointServo(config1: ServoConfig, config2: ServoConfig) {
    var servo1: MOEServo = MOEServo(config1)
    var servo2: MOEServo = MOEServo(config2)

    fun setPosition(position: Double) {
        servo1.setPosition(position)
        servo2.setPosition(position)
    }
}
