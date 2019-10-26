package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.AtomicDouble

class MOEdometrySystem(initialTheta: Double = 0.0) {
    val servos = ServosHolder()
    val wheels = WheelsHolder()
    val x: Double
        get() = atomicX.get()

    val y: Double
        get() = atomicY.get()

    val gyro: MOEdometryGyro = MOEdometryGyro(0.0)

    private val atomicX: AtomicDouble = AtomicDouble(0.0)
    private val atomicY: AtomicDouble = AtomicDouble(0.0)

    private val axialPair = MOEdometryWheelPair(wheels.axialLeft, wheels.axialRight)
    private val strafePair = MOEdometryWheelPair(wheels.strafeRight, wheels.strafeRight)

    fun reset() {

        atomicX.set(0.0)
        atomicY.set(0.0)
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
        gyro.update(angleDifference)

        //TODO: the whole point of odometry is to not use gyro
        val axialTheta = Math.toRadians(robot.gyro.angle)
        val strafeTheta = Math.toRadians(robot.gyro.angle - 90) // Transforming angle 90 degrees to the right.

        val axialVector = axialPair.getDistanceVector(angleDifference, axialTheta)
        val strafeVector = strafePair.getDistanceVector(angleDifference, strafeTheta)

        val xChange = axialVector.first + strafeVector.first
        val yChange = axialVector.second + strafeVector.second

        atomicX.add(xChange)
        atomicY.add(yChange)
    }
}
