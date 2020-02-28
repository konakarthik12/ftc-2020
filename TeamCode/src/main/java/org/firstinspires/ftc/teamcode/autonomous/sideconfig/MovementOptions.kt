package org.firstinspires.ftc.teamcode.autonomous.sideconfig

import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation

data class MovementOptions(val left: Double, val middle: Double, val right: Double, val power: Double = 0.5) {
    fun getDistance(stoneLocation: SkyStoneLocation): Double {
        return when (stoneLocation) {
            SkyStoneLocation.LEFT -> left
            SkyStoneLocation.MIDDLE -> middle
            SkyStoneLocation.RIGHT -> right
        }
    }

    constructor(allTogether: Double, power: Double = 0.5) : this(allTogether, allTogether, allTogether, power)
}
