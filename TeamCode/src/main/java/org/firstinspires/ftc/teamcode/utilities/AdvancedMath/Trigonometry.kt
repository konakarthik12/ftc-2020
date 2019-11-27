package org.firstinspires.ftc.teamcode.utilities.AdvancedMath

import kotlin.math.PI

fun toDegrees(angRad: Double): Double {
    return angRad * 180.0 / PI
}
fun toRadians(angDeg: Double): Double {
    return angDeg / 180.0 * PI
}