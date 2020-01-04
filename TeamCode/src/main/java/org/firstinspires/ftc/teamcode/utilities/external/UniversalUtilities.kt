package org.firstinspires.ftc.teamcode.utilities.external

import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.PointImpl
import org.firstinspires.ftc.teamcode.utilities.external.other.Cloneable
import kotlin.math.*

fun Double.toPrecision(digits: Int = 3): String {
    return toPrecision(this, digits.toDouble()).toString()
}

fun toPrecision(n: Double, p: Double): Double {
    if (n == 0.0) return 0.0
    val e: Double = floor(log10(abs(n)))
    val f = round(exp(abs(e - p + 1) * ln(10.0)))
    //    val e: Double = Math.floor(Math.log10(Math.abs(n)))
    //    val f: Double = Math.round(Math.exp(Math.abs(e - p + 1) * log(10)))
    return if (e - p + 1 < 0) {
        round(n * f) / f
    } else round(n / f) * f
}

 fun IntRange.coerceIn(indices: IntRange): IntRange {
    return IntRange(start.coerceAtLeast(indices.first), last.coerceAtMost(indices.last))
}

fun <T> Iterable<PointImpl<T>>.toDoubleList(): List<Double> = flatMap { listOf(it.x, it.y) }

fun <T : Cloneable<T>> Iterable<T>.clone(): List<T> = map { it.clone() }

