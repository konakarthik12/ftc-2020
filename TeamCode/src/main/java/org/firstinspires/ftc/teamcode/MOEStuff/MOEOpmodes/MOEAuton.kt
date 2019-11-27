package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot

abstract class MOEAuton : MOEOpMode() {
    final override fun moeInternalInit() {
        robot = MOEBot(this, useCamera = true, useSlam = false)

    }
    final override fun moeInternalPostInit() {
        robot.gyro.init(true)

    }
}