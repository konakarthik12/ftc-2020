package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEServo
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Odometry
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.AtomicDouble
import org.firstinspires.ftc.teamcode.utilities.wait

class MOEdometrySystem {
    val servos = ServosHolder()

    class ServosHolder {
        val left: MOEServo = MOEServo(Odometry.Servos.Configs.Left)
        val right: MOEServo = MOEServo(Odometry.Servos.Configs.Right)

        fun initServosUp() {
            left.setPosition(0.0)
            right.setPosition(0.0)
        }

        fun initServosDown() {
            initServosUp()
            moeOpMode.wait(2000)
            left.setPositionOverTime(1.0, 1.0, async = true)
            right.setPositionOverTime(1.0, 1.0, async = true)
        }
    }

    val wheels = WheelsHolder()

    class WheelsHolder {
        // axial = forward/backward motion
        // strafe = left/right motion
        val axialRight = MOEdometryWheel(Odometry.Wheels.Configs.axialRight)
        val axialLeft = MOEdometryWheel(Odometry.Wheels.Configs.axialLeft)
        val strafeRight = MOEdometryWheel(Odometry.Wheels.Configs.strafeRight)

        fun update() {
            axialLeft.update()
            axialRight.update()
            strafeRight.update()
        }
    }

    val x: Double
        get() = atomicX.get()

    val y: Double
        get() = atomicY.get()

    private val atomicX: AtomicDouble = AtomicDouble(0.0)
    private val atomicY: AtomicDouble = AtomicDouble(0.0)

    val axialPair = MOEdometryWheelPair(wheels.axialLeft, wheels.axialRight)
    val strafePair = MOEdometryWheelPair(wheels.strafeRight, wheels.strafeRight)

    fun startLocalizationThread() {
        val opMode = ReferenceHolder.moeOpMode

        GlobalScope.launch {
            while (opMode.opModeIsActive()) {
                wheels.update()
                updatePosition()
            }
        }
    }

    private fun updatePosition() {
        val angle = axialPair.getDistanceDiscordance()
        //TODO: the whole point of odometry is to not use gyro
        val axialTheta = Math.toRadians(robot.gyro.angle)
        val strafeTheta = Math.toRadians(robot.gyro.angle - 90) // Transforming angle 90 degrees to the right.

        val axialVector = axialPair.getDistanceVector(angle, axialTheta)
        val strafeVector = strafePair.getDistanceVector(angle, strafeTheta)

        val xChange = axialVector.first + strafeVector.first
        val yChange = axialVector.second + strafeVector.second

        atomicX.add(xChange)
        atomicY.add(yChange)
    }
}
