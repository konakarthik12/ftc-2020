package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Odometry.RightForward
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Odometry.Strafe
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.RotationsHandler
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.WrapperHandler
import kotlin.math.cos
import kotlin.math.sin

class MOEDometrySystem {
    val rightForwardWheel = MOEDometryWheel(RightForward)
    val strafeWheel = MOEDometryWheel(Strafe)
    val angleWrapped = WrapperHandler(360.0) { robot.gyro.angle }
    var angleWrappedValue = 0.0

    var pose = Point(0.0, 0.0)
    var fieldCentricPose  = RotationsHandler({robot.odometry.pose}, {robot.gyro.radians})
    var fieldCentricPoseValue = Point(0.0,0.0)

    fun runLoop() {

        angleWrappedValue = angleWrapped.getValue()
        val forward = rightForwardWheel.updateValue(angleWrappedValue)
        val strafe = strafeWheel.updateValue(angleWrappedValue)

        pose = Point(forward, strafe)
        fieldCentricPoseValue = fieldCentricPose.getValue()
    }

    fun launchLoop() {
        GlobalScope.launch {
            while (!moeOpMode.iIsStopRequested) {
                runLoop()
            }
        }

    }
}