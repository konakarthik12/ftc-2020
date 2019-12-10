package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import com.qualcomm.robotcore.hardware.Servo

class MOEJointServo(config1: ServoConfig, config2: ServoConfig) {
    var servo1: MOEServo = MOEServo(config1)
    var servo2: MOEServo = MOEServo(config2)

    fun setPosition(position: Double) {
        servo1.setPosition(position)
        servo2.setPosition(position)
    }
}
