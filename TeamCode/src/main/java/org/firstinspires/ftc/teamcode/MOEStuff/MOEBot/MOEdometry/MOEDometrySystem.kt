package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Odometry.RightForward
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Odometry.Strafe
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.WrapperHandler
import kotlin.math.cos
import kotlin.math.sin

class MOEDometrySystem {
    val rightForwardWheel = MOEDometryWheel(RightForward)
    val strafeWheel = MOEDometryWheel(Strafe)
    val angleWrapped = WrapperHandler(360.0) { robot.gyro.angle }
    var angleWrappedValue = 0.0

    private var forwardPrevious = 0.0
    private var forwardHolder = 0.0
    private var forwardDelta = 0.0

    private var strafePrevious = 0.0
    private var strafeHolder = 0.0
    private var strafeDelta = 0.0

    var pose = Point(0.0, 0.0)
    var fieldCentricPose  = Point(0.0,0.0)
    fun runLoop() {
        forwardDelta = forwardPrevious - forwardHolder
        strafeDelta = strafePrevious - strafeHolder

        val radAng = robot.gyro.radians

        forwardDelta = strafeDelta*sin(radAng) + forwardDelta*cos(radAng)
        strafeDelta = strafeDelta*cos(radAng) - forwardDelta*sin(radAng)

        forwardHolder += forwardDelta
        strafeHolder += strafeDelta

        angleWrappedValue = angleWrapped.getValue()
        val forward = rightForwardWheel.updateValue(angleWrapped)
        val strafe = strafeWheel.updateValue(angleWrapped)

        forwardPrevious = rightForwardWheel.updateValue(angleWrapped)
        strafePrevious = strafeWheel.updateValue(angleWrapped)

        pose = Point(forward, strafe)
        fieldCentricPose = Point(forwardHolder, strafeHolder)
    }

    fun launchLoop() {
        GlobalScope.launch {
            while (!moeOpMode.iIsStopRequested) {
                runLoop()
            }
        }

    }
}