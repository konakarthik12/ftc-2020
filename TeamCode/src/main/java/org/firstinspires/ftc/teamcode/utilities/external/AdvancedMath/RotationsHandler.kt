package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import kotlin.math.cos
import kotlin.math.sin

class RotationsHandler(val Pose: () -> Point, val radAngle: () -> Double) {

    var finalPose: Point = Point(0.0,0.0)

    var angle: Double = 0.0

    //var angle = radAngle()

    private var prevPose = finalPose

    private fun getDeltaPose(currPose: Point): Point {
        angle = radAngle()
        val deltaPose = currPose - prevPose
        return Point((deltaPose.x) * cos(angle) + (deltaPose.y)* sin(angle),
                -(deltaPose.x)*sin(angle) + (deltaPose.y)*cos(angle))
    }
    var firstRun = true
    fun getValue(): Point {
        if(firstRun){
            prevPose = Pose()
            finalPose = prevPose
            firstRun=false
            return prevPose
        }
        val currPose = Pose()
        val deltaPose = getDeltaPose(currPose)
        finalPose += deltaPose
        prevPose = currPose
        return finalPose
    }
}

fun main() {

    val rotationsHandler = RotationsHandler( {Point(readLine()?.toDouble()!!, readLine()?.toDouble()!!)}, {readLine()?.toDouble()!!.toRadians()})

    while (true) {
        println(rotationsHandler.getValue())
        println(rotationsHandler.angle.toString())
    }
}
