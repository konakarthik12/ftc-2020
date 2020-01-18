package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toRadians

data class MOEtion(var pose: Point = Point(0.0, 0.0), var degAng: Double = 0.0) {
    constructor(x: Double, y: Double, degAng: Double) : this(Point(x, y), degAng)

    val radAng
        get() = degAng.toRadians()

    override fun toString(): String {
<<<<<<< HEAD
        return "pc.getPose: $pose\n angle:$degAng"
=======
        return "pose: $pose\n angle:$degAng"
>>>>>>> d7f3725f3d1f64cd92886753c8d1efc284a9e2d8
    }

}
