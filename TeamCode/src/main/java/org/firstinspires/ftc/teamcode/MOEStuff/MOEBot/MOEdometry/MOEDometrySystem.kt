package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Odometry.RightForward
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Odometry.Strafe
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.other.WrapperHandler

class MOEDometrySystem {
    val rightFoward = MOEDometryWheel(RightForward)
    val strafe = MOEDometryWheel(Strafe)
    val angleWrapped = WrapperHandler(360) { robot.gyro.angle }
    var angleWrappedValue = 0.0
    var pose = Point(0.0, 0.0)
    fun runLoop() {
        angleWrappedValue = angleWrapped.getValue()
        val forward = rightFoward.updateValue(angleWrapped)
        val strafe = rightFoward.updateValue(angleWrapped)
        pose = Point(forward, strafe)
    }

    fun launchLoop() {
        GlobalScope.launch {
            while (!moeOpMode.iIsStopRequested) {
                runLoop()
            }
        }

    }
}