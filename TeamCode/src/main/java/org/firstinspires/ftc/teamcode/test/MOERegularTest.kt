package org.firstinspires.ftc.teamcode.test

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOERegularOpMode

abstract class MOERegularTest() : MOERegularOpMode() {

     override fun moeInternalInit() {
        if (getRobotConfig().useGyro) {
            robot.gyro.init()
        }
    }


    final override fun moeInternalPostInit() {
        if (getRobotConfig().useGyro) {
            robot.gyro.init(true)
        }
    }


}
