package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toRadians

data class Transformation(val pose: Point,val degAng: Double) {
    val radAng
        get() = degAng.toRadians()

}
