package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot

abstract class MOETeleOp(val useSlam: Boolean = false) : MOEOpMode() {
    final override fun moeInternalInit() {
        robot = MOEBot(opMode = this, useOdometryForGyro = false, useSlam = useSlam)
    }

    final override fun run() {
        while (opModeIsActive()) {
            loopStuff()
        }
    }

    abstract fun loopStuff()
}