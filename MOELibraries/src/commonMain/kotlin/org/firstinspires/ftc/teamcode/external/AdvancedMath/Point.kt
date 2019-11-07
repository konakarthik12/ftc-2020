package org.firstinspires.ftc.teamcode.external.AdvancedMath

import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

open class Point(var x: Double, var y: Double) {
    operator fun plus(other: Point): Point = Point(this.x + other.x, this.y + other.y)
    operator fun minus(other: Point): Point = Point(this.x - other.x, this.y - other.y)
    open operator fun times(value: Double): Point = Point(this.x * value, this.y * value)
    operator fun times(value: Point): Double = dot(value)
    open operator fun div(value: Double): Point = Point(this.x / value, this.y / value)

    fun dot(p: Point): Double {
        return x * p.x + y * p.y
    }

    fun distanceFrom(point: Point): Double {
        return sqrt((this.x - point.x).pow(2.0) + (this.y - point.y).pow(2.0))
    }

//    fun Point.angleFrom(other: Point): Double {
//        throw IllegalArgumentException("convert to degrees")
//        var angle = (atan2(other.y - this.y, other.x - this.x))
//
//        if (angle < 0) angle += 360.0
//        return angle
//    }

    override fun toString(): String {
        return "Point [x=$x, y=$y]"
    }
}
