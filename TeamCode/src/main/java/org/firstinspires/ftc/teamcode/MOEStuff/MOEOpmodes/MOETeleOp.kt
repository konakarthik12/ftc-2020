package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEGamepad

abstract class MOETeleOp(val thetaOffset: Double = 0.0, val useSlam: Boolean = false) : MOERegularOpMode() {
    lateinit var gpad1: MOEGamepad
    lateinit var gpad2: MOEGamepad
    //    var gpad1 = MOEGamepad(gamepad1)
    //    var gpad2 = MOEGamepad(gamepad2)

    final override fun moeInternalInit() {
        robot = MOEBot(opMode = this, useSlam = useSlam, thetaOffset = thetaOffset)
        gpad1 = MOEGamepad(gamepad1)
        gpad2 = MOEGamepad(gamepad2)
    }

    final override fun moeInternalPostInit() {
        robot.gyro.init(true)
    }

    override fun internalLoop() {
        gpad1.update()
        gpad2.update()
        mainLoop()
    }

    abstract fun mainLoop()
}
