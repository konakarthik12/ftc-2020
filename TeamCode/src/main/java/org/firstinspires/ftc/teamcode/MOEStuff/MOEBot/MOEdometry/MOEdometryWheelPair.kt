package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.and
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class MOEdometryWheelPair(private val first: MOEdometryWheel, private val second: MOEdometryWheel) {
    private val averageDistanceChanged: Double
        get() = (first.getDistanceChanged() + second.getDistanceChanged()) / 2

    fun getAngleDifference(): Double {
        val avgDistanceChanged = averageDistanceChanged
        val firstDistanceChanged = first.getDistanceChanged()
        val secondDistanceChanged = second.getDistanceChanged()
        val firstDifference = abs(firstDistanceChanged - avgDistanceChanged)
        val secondDifference = abs(secondDistanceChanged - avgDistanceChanged)

        val avgDifference = (firstDifference + secondDifference) / 2
        val percentage = avgDifference / first.circumference

        return if (firstDistanceChanged > 0) -percentage else percentage
    }

    fun getDistanceVector(angleDifference: Double, theta: Double): Pair<Double, Double> {
        val distanceRemoval = first.circumference * angleDifference
        // TODO: Determine whether this is a + or a - !!!
        val distanceChanged = first.getDistanceChanged() - distanceRemoval

        val x = cos(theta) * distanceChanged
        val y = sin(theta) * distanceChanged

        return x and y
    }
}
