package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.OutTakeSystem.Servos.Configs

class MOEOuttake {
    val grabServo = MOEServo(Configs.GrabberServo)
    val outtakeServo = MOEServo(Configs.OuttakeServo)

//    init {
//        moveIn()
//    }

    fun moveIn() {
        outtakeServo.setPosition(0.0)
    }

    fun moveOut() {
        outtakeServo.setPosition(1.0)
    }
}
