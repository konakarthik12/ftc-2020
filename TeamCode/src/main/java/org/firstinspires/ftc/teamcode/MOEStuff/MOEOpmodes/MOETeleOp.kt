package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot

abstract class MOETeleOp(val thetaOffset: Double = 0.0, val useSlam: Boolean = false) : MOEOpMode() {
    final override fun moeInternalInit() {
        robot = MOEBot(opMode = this, useSlamForGyro = useSlam, useSlam = useSlam, thetaOffset = thetaOffset)
    }

    final override fun moeInternalPostInit() {
        robot.gyro.init(true)

    }

    final override fun run() {
        while (opModeIsActive()) {
            loopStuff()
        }
    }

    abstract fun loopStuff()
}