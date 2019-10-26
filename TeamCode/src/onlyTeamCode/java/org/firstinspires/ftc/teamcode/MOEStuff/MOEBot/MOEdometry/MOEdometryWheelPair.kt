package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.and
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class MOEdometryWheelPair(private val first: MOEdometryWheel, private val second: MOEdometryWheel) {
    private val averageDistanceChanged: Double
        get() = (first.getDistanceChanged() + second.getDistanceChanged()) / 2

    fun getDistanceDiscordance(): Double {
        val avgDistanceChanged = averageDistanceChanged
        val firstDifference = abs(first.getDistanceChanged() - avgDistanceChanged)
        val secondDifference = abs(second.getDistanceChanged() - avgDistanceChanged)
        val avgDifference = (firstDifference + secondDifference) / 2
        return avgDifference / first.circumference
    }

    fun getDistanceVector(discordance: Double, theta: Double): Pair<Double, Double> {
        val distanceRemoval = first.circumference * discordance
        // TODO: Determine whether this is a + or a - !!!
        val distanceChanged = first.getDistanceChanged() - distanceRemoval

        val x = cos(theta) * distanceChanged
        val y = sin(theta) * distanceChanged

        return x and y
    }
}
