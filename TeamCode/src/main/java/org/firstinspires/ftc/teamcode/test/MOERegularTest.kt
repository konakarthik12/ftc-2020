package org.firstinspires.ftc.teamcode.test

import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOERegularOpMode

abstract class MOERegularTest : MOERegularOpMode() {

     override fun moeInternalInit() {
        if (getRobotSubSystemConfig().useGyro) {
            robot.gyro.init()
        }
    }


    final override fun moeInternalPostInit() {
        if (getRobotSubSystemConfig().useGyro) {
            robot.gyro.init(true)
        }
    }


}
