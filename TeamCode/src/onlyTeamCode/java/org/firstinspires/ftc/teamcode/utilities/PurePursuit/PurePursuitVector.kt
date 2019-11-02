package org.firstinspires.ftc.teamcode.utilities.PurePursuit

import org.firstinspires.ftc.teamcode.utilities.Point
import kotlin.math.sqrt

class PurePursuitVector(x: Double, y: Double) : Point(x, y) {
    operator fun plus(other: PurePursuitVector): PurePursuitVector = PurePursuitVector(this.x + other.x, this.y + other.y)
    operator fun minus(other: PurePursuitVector): PurePursuitVector = PurePursuitVector(this.x - other.x, this.y - other.y)
    override operator fun times(value: Double): PurePursuitVector = PurePursuitVector(this.x * value, this.y * value)
    operator fun times(value: PurePursuitVector): Double = dot(value)
    override operator fun div(value: Double): PurePursuitVector = PurePursuitVector(this.x / value, this.y / value)

    val magnitude: Double
        get() = sqrt(x * x + y * y)

    constructor(point: Point) : this(point.x, point.y)

    fun normalize() {
        this.x /= this.magnitude
        this.y /= this.magnitude
    }
}
