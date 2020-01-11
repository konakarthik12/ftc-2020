package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toRadians

data class Transformation(val pose: Point = Point(0.0, 0.0)
                          , val degAng: Double = 0.0) {
    constructor(x: Double, y: Double, degAng: Double) : this(Point(x, y), degAng)

    val radAng
        get() = degAng.toRadians()

    override fun toString(): String {
        return "pose: $pose\n angle:$degAng"
    }

}
