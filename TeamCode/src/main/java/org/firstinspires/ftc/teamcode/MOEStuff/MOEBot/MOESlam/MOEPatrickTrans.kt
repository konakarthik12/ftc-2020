package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBotConstants
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEtion
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.*
import kotlin.math.cos
import kotlin.math.sin

class MOEPatrickTrans(val config: MOEBotConstants) {
    private val slamConfig = config.getSlamConfig()
    private val initialCameraTrans = findInitialCameraTrans()
    private val robotInitial = config.getRobotInitialState().robotInitial
    private fun findInitialCameraTrans(): MOEtion {
        val radTheta = robotInitial.radAng + slamConfig.ROBOT_TO_CAMERA_DIST.radAng
        val pose = robotInitial.pose.getRelativePoint(slamConfig.ROBOT_TO_CAMERA_DIST.r, radTheta)
        val angle = (robotInitial.degAng + slamConfig.ROBOT_TO_CAMERA_THETA).toNormalAngle()
        return MOEtion(pose, angle)
    }

    fun getCameraTrans(slamRawPose: MOEtion): MOEtion {
        val pose = initialCameraTrans.getPatrickAxesRotationOffset(slamRawPose.pose)
        val angle = (initialCameraTrans.degAng + slamRawPose.degAng).toNormalAngle()
        return MOEtion(pose, angle)
    }

    fun getRobotTrans(slamPose: MOEtion): MOEtion {
        val radTheta = slamPose.radAng + slamConfig.ROBOT_TO_CAMERA_DIST.radAng
        val pose = slamPose.pose.getRelativePoint(slamConfig.ROBOT_TO_CAMERA_DIST.r, radTheta)
        val angle = (slamPose.degAng - slamConfig.ROBOT_TO_CAMERA_THETA).toNormalAngle()
        return MOEtion(pose, angle)
    }

    fun getTrans(rawSlamTrans: MOEtion): MOEtion {
        val cameraTrans = getCameraTrans(rawSlamTrans)
        return getRobotTrans(cameraTrans)
    }

}

fun MOEtion.getPatrickAxesRotationOffset(pointFromThis: Point): Point {

    val xOffset = pointFromThis.y * sin(this.radAng) + pointFromThis.x * cos(this.radAng)
    val yOffset = pointFromThis.y * cos(this.radAng) - pointFromThis.x * sin(this.radAng)
    return pointFromThis.create(this.pose.x + xOffset, this.pose.y + yOffset)
}
/*
fun main() {
    val moePatrickTrans = MOEPatrickTrans(MOEPatrickSlamConfig(robotInitial = Transformation(10.0, 10.0,90.0)))
    val moePatrickRawCameraTrans = Transformation(20.0, -10.0, -90.0)
    val moePatrickCameraTrans = moePatrickTrans.getCameraTrans(moePatrickRawCameraTrans)

    println("Initial Camera Pose: " + moePatrickTrans.initialCameraTrans.toString())
    println("Camera Pose: " + moePatrickCameraTrans)
    println("Robot Pose: " + moePatrickTrans.getTrans(moePatrickCameraTrans))

}
*/