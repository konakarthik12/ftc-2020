package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import android.util.Log
import kotlinx.coroutines.*
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBotConstants
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEtion
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Odometry.RightForward
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Odometry.Strafe
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.WrapperHandler
import kotlin.math.cos
import kotlin.math.sin


class MOEDometrySystem(val config: MOEBotConstants) : MOELocalizationSystem {

    override fun moetion(): MOEtion {
        return MOEtion(fieldCentricPose, robot.gyro.angle)
    }

    val rightForwardWheel = MOEDometryWheel(RightForward)
    val strafeWheel = MOEDometryWheel(Strafe)
    val angleWrapped = WrapperHandler(360.0) { robot.gyro.getRawAngle() }
    var angleWrappedValue = 0.0

    fun resetValues() {
        rightForwardWheel.resetValues()
        strafeWheel.resetValues()
    }

    var pose = Point(0.0, 0.0)
    //    var fieldCentricPose  = RotationsHandler({robot.odometry.pc.getPose}, {robot.gyro.radians})
    //    var fieldCentricPose  = RotationsHandler({robot.odometry.pose}, {robot.gyro.radians})
    var fieldCentricPose = (config.getRobotInitialState().robotInitial.pose.clone()) / 2.0
    var prevPose = Point(0.0, 0.0)

    fun runLoop() {
//        val currTime = System.currentTimeMillis()
        angleWrappedValue = angleWrapped.getValue()
        val forward = rightForwardWheel.getFixedValue(angleWrappedValue)
        val strafe = strafeWheel.getFixedValue(angleWrappedValue)

        pose = Point(forward, strafe)
        fieldCentricPose += getDeltaPose(pose)
//        telemetry.addData("odoTime", System.currentTimeMillis() - currTime)
    }

    private fun getDeltaPose(currPose: Point): Point {
        val angle = robot.gyro.radAng
        val deltaPose = currPose - prevPose
        prevPose = currPose
        return Point(
                (deltaPose.x) * sin(angle) + (deltaPose.y) * cos(angle),
                (deltaPose.x) * cos(angle) - (deltaPose.y) * sin(angle))
    }

    lateinit var launch: Job
    fun launchLoop() {
        launch = GlobalScope.launch {
            //todo: will fail if app crashes
            while (isActive && !moeOpMode.iIsStopRequested) {
                runLoop()
                Log.v("run", "running")
            }
        }
    }

    fun stop() {
        launch.cancel()
    }
}
