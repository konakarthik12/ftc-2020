package org.firstinspires.ftc.teamcode.external.AdvancedMath

import org.firstinspires.ftc.teamcode.utilities.toFixed
import kotlin.math.*

open class Point(var x: Double, var y: Double) {
    operator fun plus(other: Point): Point = Point(this.x + other.x, this.y + other.y)
    operator fun minus(other: Point): Point = Point(this.x - other.x, this.y - other.y)
    open operator fun times(value: Double): Point = Point(this.x * value, this.y * value)
    operator fun times(value: Point): Double = dot(value)
    open operator fun div(value: Double): Point = Point(this.x / value, this.y / value)

    fun dot(p: Point): Double {
        return x * p.x + y * p.y
    }

    /**
     * creates another point relative to this one given distance and angle (Polar style)
     */
    fun getRelativePoint(distanceFromThis: Double, theta: Double): Point {
        val cameraX = cos(theta) * distanceFromThis
        val cameraY = sin(theta) * distanceFromThis

        return Point(this.x + cameraX, this.y + cameraY)
    }

    fun distanceFrom(point: Point): Double {
        return sqrt((this.x - point.x).pow(2.0) + (this.y - point.y).pow(2.0))
    }

    /** clockwise rotation */
    fun rotateAroundOrigin(angle: Double): Point {
        val x = x * cos(angle) - y * sin(angle)
        val y = x * sin(angle) + y * cos(angle)
        return Point(x, y)
    }

    override fun toString(): String {
        return "Point [x=${x.toFixed(3)}, y=${y.toFixed(3)}]"
    }
}
