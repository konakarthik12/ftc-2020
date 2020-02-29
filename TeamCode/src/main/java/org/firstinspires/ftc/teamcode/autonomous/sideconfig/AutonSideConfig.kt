package org.firstinspires.ftc.teamcode.autonomous.sideconfig

import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle

data class AutonSideConfig(val movements: List<MovementOptions>, val cropRectangle: Rectangle) {
    fun getDistances(skyStoneLocation: SkyStoneLocation): List<Movement> {
        return movements.map { it.getDistance(skyStoneLocation) }
    }

}
