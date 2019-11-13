package org.firstinspires.ftc.teamcode.utilities

import kotlin.math.asin
import kotlin.math.atan2

fun quaternionToHeading(q: DoubleArray): Double = quaternionToHeading(q[0], q[1], q[2], q[3])

fun quaternionToHeading(qw: Double, qx: Double, qy: Double, qz: Double): Double {
    val sqw = qw * qw
    val sqx = qx * qx
    val sqy = qy * qy
    val sqz = qz * qz
    val heading = atan2(2.0 * (qx * qy + qz * qw), sqx - sqy - sqz + sqw)
    val bank = atan2(2.0 * (qy * qz + qx * qw), -sqx - sqy + sqz + sqw)
    val attitude = asin(-2.0 * (qx * qz - qy * qw) / (sqx + sqy + sqz + sqw))
    return attitude
}
