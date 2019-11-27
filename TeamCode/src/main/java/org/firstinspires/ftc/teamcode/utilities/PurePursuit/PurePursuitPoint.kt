package org.firstinspires.ftc.teamcode.utilities.PurePursuit

import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Point
import kotlin.math.pow
import kotlin.math.sqrt

class PurePursuitPoint(x: Double, y: Double) : Point(x, y) {
    var prefixDistance: Double = 0.0
    var velocity: Double = 0.0
    var isCriticalPoint: Boolean = false
    var magnitude: Double = 0.0

    constructor(point: Point) : this(point.x, point.y)

    override fun toString(): String = "Point [x=$x, y=$y, velocity=$velocity]"

    fun simpleString(): String = super.toString()

    companion object {
        fun getCurvatureOfPoints(leftPoint: PurePursuitPoint, centerPoint: PurePursuitPoint, rightPoint: PurePursuitPoint): Double {
            if (centerPoint.x == 90.0) {
                centerPoint.x += 0.0001
            } //Fixing for DivideByZero error

            val k1 = 0.5 * (centerPoint.x * centerPoint.x + centerPoint.y * centerPoint.y - leftPoint.x * leftPoint.x - leftPoint.y * leftPoint.y) / (centerPoint.x - leftPoint.x)
            val k2 = (centerPoint.y - leftPoint.y) / (centerPoint.x - leftPoint.x)
            val b = 0.5 * (leftPoint.x * leftPoint.x - 2.0 * leftPoint.x * k1 + leftPoint.y * leftPoint.y - rightPoint.x * rightPoint.x + 2.0 * rightPoint.x * k1 - rightPoint.y * rightPoint.y) / (rightPoint.x * k2 - rightPoint.y + leftPoint.y - leftPoint.x * k2)
            val a = k1 - k2 * b
            val r = sqrt((centerPoint.x - a).pow(2.0) + (centerPoint.y - b).pow(2.0))
            val curvature = 1 / r
            return if (curvature.isNaN()) 0.0 else curvature
        }
    }
}
