package org.firstinspires.ftc.teamcode.utilities.PurePursuit

import org.firstinspires.ftc.teamcode.utilities.Point

fun getSignedCurvatureFromLookaheadPoint(lookahead: Point, currPos: Point,
                                         heading: Double, lookaheadDistance: Double): Double {
    var heading = heading
    if (heading == 90.0 || heading == 270.0) {
        heading += 0.0001
    }
    heading = Math.toRadians(heading)

    val a = -Math.tan(heading)
    val b = 1.0
    val c = Math.tan(heading) * currPos.x - currPos.y
    val x = Math.abs(a * lookahead.x + b * lookahead.y + c) / Math.sqrt(Math.pow(a, 2.0) + Math.pow(b, 2.0))
    val cross = Math.sin(heading) * (lookahead.x - currPos.x) - Math.cos(heading) * (lookahead.y - currPos.y)
    val side = (if (cross > 0) 1 else -1).toDouble()
    val curvature = 2 * x / Math.pow(lookaheadDistance, 2.0)
    return curvature * side
}