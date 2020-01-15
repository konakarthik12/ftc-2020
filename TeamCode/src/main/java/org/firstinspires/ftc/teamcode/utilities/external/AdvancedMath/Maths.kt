package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import kotlin.math.pow
import kotlin.math.sqrt


//fun closestAngleDifference(source: Double, target: Double): Double = ((target - source) + 180) % 360 - 180
fun Double.closestAngleDifference(other: Double): Double {
    return (other - this + 540) % 360 - 180
    //    return 180 - kotlin.math.abs(kotlin.math.abs(a1 - a2) - 180);
}


fun <T : PointImpl<T>> getCircleLineIntersection(pointA: PointImpl<T>, pointB: PointImpl<T>, circleCenter: PointImpl<T>, radius: Double): Double {
    val d = pointB - pointA
    val f = pointA - circleCenter

    val a = d.dot(d)
    val b = 2 * (f.dot(d))
    val c = (f.dot(f)) - radius.pow(2.0)
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


fun Double.toDegrees() = Math.toDegrees(this)
fun Double.toRadians() = Math.toRadians(this)
