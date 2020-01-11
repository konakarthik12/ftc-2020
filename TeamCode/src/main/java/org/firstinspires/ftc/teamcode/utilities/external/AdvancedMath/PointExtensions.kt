package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import org.firstinspires.ftc.teamcode.utilities.external.PurePursuit.PurePursuitPoint
import kotlin.math.*


/**
 * creates another point relative to this one given distance and angle
 *
 * 0 is straight up
 * 90 is to the right
 * 180 is down
 * 270 is lef
 */
fun <T : PointImpl<T>> T.getRelativePoint(distanceFromThis: Double, radTheta: Double): T {
    val cameraX = sin(radTheta) * distanceFromThis
    val cameraY = cos(radTheta) * distanceFromThis

    return create(this.x + cameraX, this.y + cameraY)
}

/** clockwise rotation */
fun <T : PointImpl<T>> T.rotateAroundOrigin(radAng: Double): T {
    val newX = x * cos(radAng) - y * sin(radAng)
    val newY = x * sin(radAng) + y * cos(radAng)
    return create(newX, newY)
}

fun <T : PointImpl<T>> T.distanceFrom(point: T) = hypot(this.x - point.x, this.y - point.y)
//    return sqrt((this.x - point.x).pow(2.0) + (this.y - point.y).pow(2.0))

fun <T : PointImpl<T>> Iterable<T>.toLineSegmentList() = zipWithNext { a, b -> a..b }

operator fun <T : PointImpl<T>> T.rangeTo(b: T) = LineSegment(this, b)

fun <T : PointImpl<T>> PointImpl<T>.toPurePursuitPoint() = PurePursuitPoint(x, y)

