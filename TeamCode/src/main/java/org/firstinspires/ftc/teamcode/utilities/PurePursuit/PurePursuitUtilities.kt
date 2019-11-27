package org.firstinspires.ftc.teamcode.utilities.PurePursuit

import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.toRadians
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Point
import kotlin.math.*

fun getSignedCurvatureFromLookaheadPoint(lookahead: Point, currPos: Point,
                                         heading: Double, lookaheadDistance: Double): Double {
    var radianHeading = heading
    if (radianHeading == 90.0 || radianHeading == 270.0) {
        radianHeading += 0.0001
    }
    radianHeading = toRadians(radianHeading)

    val a = -tan(radianHeading)
    val b = 1.0
    val c = tan(radianHeading) * currPos.x - currPos.y
    val x = abs(a * lookahead.x + b * lookahead.y + c) / sqrt(a.pow(2.0) + b.pow(2))
    val cross = sin(radianHeading) * (lookahead.x - currPos.x) - cos(radianHeading) * (lookahead.y - currPos.y)
    val side = (if (cross > 0) 1 else -1).toDouble()
    val curvature = 2 * x / lookaheadDistance.pow(2.0)
    return curvature * side
}