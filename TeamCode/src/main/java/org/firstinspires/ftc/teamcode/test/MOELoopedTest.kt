package org.firstinspires.ftc.teamcode.test

import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEGamepad.MOEGamepad

abstract class MOELoopedTest : MOERegularTest() {
    lateinit var gpad1: MOEGamepad
    lateinit var gpad2: MOEGamepad
    override fun moeInternalInit() {
        super.moeInternalInit()
        setUpJoysticks()
    }
    private fun setUpJoysticks() {
        //        gamepad1.setJoystickDeadzone(0.05f)
        //        gamepad2.setJoystickDeadzone(0.05f)
        gpad1 = MOEGamepad(gamepad1)
        gpad2 = MOEGamepad(gamepad2)

    }

    override fun run() {

        while (opModeIsActive()) {
            gpad1.update()
            gpad2.update()
            mainLoop()
        }
    }

    abstract fun mainLoop()
}