package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import org.firstinspires.ftc.teamcode.utilities.external.PurePursuit.PurePursuitPoint
import kotlin.math.*


/**
 * creates another point relative to this one given distance and angle (Polar style)
 */
fun <T : PointImpl<T>> T.getRelativePoint(distanceFromThis: Double, theta: Double): T {
    val cameraX = cos(theta) * distanceFromThis
    val cameraY = sin(theta) * distanceFromThis

    return create(this.x + cameraX, this.y + cameraY)
}

/** clockwise rotation */
fun <T : PointImpl<T>> T.rotateAroundOrigin(angle: Double): T {
    val x = x * cos(angle) - y * sin(angle)
    val y = x * sin(angle) + y * cos(angle)
    return create(x, y)
}

fun <T : PointImpl<T>> T.distanceFrom(point: T) = hypot(this.x - point.x, this.y - point.y)
//    return sqrt((this.x - point.x).pow(2.0) + (this.y - point.y).pow(2.0))

fun <T : PointImpl<T>> Iterable<T>.toLineSegmentList() = zipWithNext { a, b -> a..b }

operator fun <T : PointImpl<T>> T.rangeTo(b: T) = LineSegment(this, b)

fun <T : PointImpl<T>> PointImpl<T>.toPurePursuitPoint() = PurePursuitPoint(x, y)

