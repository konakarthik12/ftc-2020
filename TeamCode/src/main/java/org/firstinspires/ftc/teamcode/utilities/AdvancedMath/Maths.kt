package org.firstinspires.ftc.teamcode.utilities.AdvancedMath

import kotlin.math.pow
import kotlin.math.sqrt


//fun closestAngleDifference(source: Double, target: Double): Double = ((target - source) + 180) % 360 - 180
infix fun Double.closestAngleDifference(other: Double): Double = ((other - this) + 180) % 360 - 180


fun getCircleLineIntersection(pointA: Point, pointB: Point, circleCenter: Point, radius: Double): Double {
    val d = pointB - pointA
    val f = pointA - circleCenter

    val a = d * d
    val b = 2 * (f * d)
    val c = (f * f) - radius.pow(2.0)
    var discriminant = b.pow(2.0) - 4.0 * a * c

    if (discriminant < 0) {
        return Double.NaN
    } else {
        discriminant = sqrt(discriminant)
        val t1 = (-b - discriminant) / (2 * a)
        val t2 = (-b + discriminant) / (2 * a)
        if (t1 in 0.0..1.0) return t1
        else if (t2 in 0.0..1.0) return t2
    }
    return Double.NaN
}

infix fun <A, B> A.and(second: B): Pair<A, B> {
    return Pair(this, second)
}

fun ClosedFloatingPointRange<Double>.lerp(t: Double): Double {
    return this.start + t * (this.endInclusive - this.start)
}

