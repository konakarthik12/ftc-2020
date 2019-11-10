package org.firstinspires.ftc.teamcode.external.AdvancedMath

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
//import kotlin.text.format

//fun round()
//fun lerp(min: Doux`ble, max: Double, f: Double) = min + f * (max - min)

//fun clamp(x: Double, a: Double, b: Double): Double = if (x < a) a else min(x, b)


fun closestAngleDifference(ang1: Double, ang2: Double): Double {
    val difference = abs(ang2 - ang1)
    val secondDifference = 360 - difference
    val returnVal = if (difference < secondDifference) difference else secondDifference

    val actualDiff = ang2 - ang1
    return when {
        -180 < actualDiff && actualDiff < 0 -> -returnVal
        actualDiff > 180 -> -returnVal
        else -> returnVal
    }

}

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
