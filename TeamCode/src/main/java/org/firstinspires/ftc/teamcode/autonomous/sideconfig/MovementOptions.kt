package org.firstinspires.ftc.teamcode.autonomous.sideconfig

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Direction
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation

data class Movement(val direction: Direction, val distance: Double, val power: Double?)
data class MovementOptions(val direction: Direction, val left: Double, val middle: Double, val right: Double, val power: Double? = null) {
    fun getDistance(stoneLocation: SkyStoneLocation): Movement {
        val distance = when (stoneLocation) {
            SkyStoneLocation.LEFT -> left
            SkyStoneLocation.MIDDLE -> middle
            SkyStoneLocation.RIGHT -> right
        }
        return Movement(direction, distance, power)
    }

    constructor(direction: Direction, allTogether: Double, power: Double = 0.5) : this(direction, allTogether, allTogether, allTogether, power)
}
