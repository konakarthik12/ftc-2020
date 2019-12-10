package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEGamepad

abstract class MOETeleOp(val thetaOffset: Double = 0.0, val useSlam: Boolean = false) : MOERegularOpMode() {
    var gpad1 = MOEGamepad(gamepad1)
    var gpad2 = MOEGamepad(gamepad2)

    final override fun moeInternalInit() {
        robot = MOEBot(opMode = this, useSlam = useSlam, thetaOffset = thetaOffset)
    }

    final override fun moeInternalPostInit() {
        robot.gyro.init(true)
    }

    override fun mainLoop() {
        gpad1.update()
        gpad2.update()
        controllerLoop()
    }

    abstract fun controllerLoop()
}
