package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import org.firstinspires.ftc.teamcode.utilities.external.other.Cloneable

interface PointImpl<T> : Cloneable<T> {
    var x: Double
    var y: Double
    operator fun plus(other: PointImpl<T>): T = create(this.x + other.x, this.y + other.y)
    operator fun minus(other: PointImpl<T>): T = create(this.x - other.x, this.y - other.y)
    operator fun times(value: Double): T = create(this.x * value, this.y * value)
    operator fun div(value: Double): Point = Point(this.x / value, this.y / value)

    operator fun timesAssign(value: Double) {
        this.x *= value; this.y *= value
    }

    fun atan2() = kotlin.math.atan2(y, x)

    fun dot(p: PointImpl<T>): Double {
        return x * p.x + y * p.y
    }

    operator fun component1(): Double = x
    operator fun component2(): Double = y

    operator fun get(index: Int): Double {
        return when (index) {
            0 -> x
            1 -> y
            else -> throw IllegalStateException("Point only has two components (x and y)")
        }
    }

    fun create(x: Double, y: Double): T
    override fun clone(): T = create(x, y)
    operator fun set(index: Int, value: Double) {
        when (index) {
            0 -> x=value
            1 -> y=value
            else -> throw IllegalStateException("Point only has two components (x and y)")
        }
    }


}
