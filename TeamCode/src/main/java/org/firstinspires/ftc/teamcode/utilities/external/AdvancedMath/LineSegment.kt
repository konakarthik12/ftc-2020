package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import kotlin.math.ceil

class LineSegment<T:PointImpl<T>>(val start: T, val end: T) {
    val length = start.distanceFrom(end)
    fun injectPoints(spacing: Double): List<T> {
        //        val vector = PurePursuitVector(points[i + 1] - points[i])

        val numberOfPointsThatFit = ceil(length / spacing).toInt()
        return List(numberOfPointsThatFit) {
            lerp(it / numberOfPointsThatFit.toDouble())
        }
        //        vector.normalize()
        //        vector *= options.spacing

        //        val originalPoint = PurePursuitPoint(points[i].x, points[i].y)
        //        originalPoint.isCriticalPoint = true
        //        newPathing.add(originalPoint)
        //
        //        for (a in 1 until numberOfPointsThatFit) {
        //            newPathing.add(
        //                    PurePursuitPoint(
        //                            points[i].x + vector.x * a,
        //                            points[i].y + vector.y * a
        //                    )
        //            )
    }

    fun lerp(t: Double): T {
        return start.create(
                (start.x..end.x).lerp(t),
                (start.y..end.y).lerp(t)
        )

    }
}


