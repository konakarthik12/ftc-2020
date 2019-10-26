package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEServo
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.utilities.wait

class ServosHolder {
    val left: MOEServo = MOEServo(MOEConstants.Odometry.Servos.Configs.Left)
    val right: MOEServo = MOEServo(MOEConstants.Odometry.Servos.Configs.Right)

    fun initServosUp() {
        left.setPosition(0.0)
        right.setPosition(0.0)
    }

    fun initServosDown() {
        initServosUp()
        ReferenceHolder.moeOpMode.wait(2000)
        left.setPositionOverTime(1.0, 1.0, async = true)
        right.setPositionOverTime(1.0, 1.0, async = true)
    }
}