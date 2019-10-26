package org.firstinspires.ftc.teamcode.utilities.PurePursuit

import kotlin.math.sqrt

class PurePursuitVector(private var x: Double, private var y: Double) {
    var magnitude: Double = 0.0


    init {
        updateMagnitude()
    }

    fun setX(x: Double) {
        this.x = x
        updateMagnitude()
    }

    fun setY(y: Double) {
        this.y = y
        updateMagnitude()
    }

    fun getX(): Double {
        return x
    }

    fun getY(): Double {
        return y
    }

    fun updateMagnitude() {
        this.magnitude = sqrt(x * x + y * y)
    }

    fun normalize() {
        this.x /= this.magnitude
        this.y /= this.magnitude
    }

    fun multiplyBy(scalar: Double) {
        this.x *= scalar
        this.y *= scalar
    }

    companion object {

        fun addVectors(a: PurePursuitVector, b: PurePursuitVector): PurePursuitVector {
            return PurePursuitVector(a.x + b.x, a.y + b.y)
        }

        fun subtractVectors(a: PurePursuitVector, b: PurePursuitVector): PurePursuitVector {
            return PurePursuitVector(a.x - b.x, a.y - b.y)
        }
    }
}
