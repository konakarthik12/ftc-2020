package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEAutonArm

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.AutonArmConfig

class MOEAutonArm(val config: AutonArmConfig, val isLeft: Boolean) {

    val clawServo = config.getClawServo()
    val armServo = config.getArmServo()
    fun openClaw() {
        clawServo.setPosition(0.0)
    }

    fun closeClaw() {
        clawServo.setPosition(1.0)
    }

    fun raiseArm() {
        armServo.setPosition(0.0)
    }

    fun lowerArm() {
        armServo.setPosition(1.0)
    }

}