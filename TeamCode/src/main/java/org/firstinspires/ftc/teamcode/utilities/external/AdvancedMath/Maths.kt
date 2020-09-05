package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import org.firstinspires.ftc.teamcode.utilities.external.purepursuit.math.PPPoint
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt


fun Double.closestAngleDifference(other: Double) = (other - this + 540) % 360 - 180




fun ClosedFloatingPointRange<Double>.lerp(t: Double): Double {
    return this.start + t * (this.endInclusive - this.start)
}


fun Double.toDegrees() = this * 180 / PI
fun Double.toRadians() = this / 180.0 * PI
