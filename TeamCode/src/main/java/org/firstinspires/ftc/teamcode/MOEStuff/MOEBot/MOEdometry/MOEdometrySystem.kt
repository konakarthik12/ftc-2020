package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.external.AdvancedMath.Point

class MOEdometrySystem {
    val servos = ServosHolder()
    private val wheels = WheelsHolder()

    val x: Double
        get() = atomicX

    val y: Double
        get() = atomicY

    val position
        get() = Point(x, y)

//    val gyro: MOEdometryGyro = MOEdometryGyro()

    @Volatile
    private var atomicX = 0.0

    @Volatile
    private var atomicY = 0.0

    private val axialPair = MOEdometryWheelPair(wheels.axialLeft, wheels.axialRight)
    private val strafePair = MOEdometryWheelPair(wheels.strafeRight, wheels.strafeRight)

    fun reset() {
        atomicX = 0.0
        atomicY = 0.0
    }

    fun init() {
        val opMode = ReferenceHolder.moeOpMode

        GlobalScope.launch {
            while (opMode.opModeIsActive()) {
                wheels.update()
                updatePosition()
            }
        }
    }

    private fun updatePosition() {
        val angleDifference = axialPair.getAngleDifference()
//        gyro.update(angleDifference)

        //TODO: the whole point of odometry is to not use gyro
        val axialTheta = Math.toRadians(robot.gyro.angle)
        val strafeTheta = Math.toRadians(robot.gyro.angle - 90) // Transforming angle 90 degrees to the right.

        val axialVector = axialPair.getDistanceVector(angleDifference, axialTheta)
        val strafeVector = strafePair.getDistanceVector(angleDifference, strafeTheta)

        val xChange = axialVector.first + strafeVector.first
        val yChange = axialVector.second + strafeVector.second

        atomicX += xChange
        atomicY += yChange
    }
}
