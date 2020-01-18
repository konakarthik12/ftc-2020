package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEServo

class AutonArmConfig(val armServoConfig: ServoConfig, val clawServoConfig: ServoConfig) {
    fun getArmServo() = MOEServo(armServoConfig)
    fun getClawServo() = MOEServo(clawServoConfig)

}