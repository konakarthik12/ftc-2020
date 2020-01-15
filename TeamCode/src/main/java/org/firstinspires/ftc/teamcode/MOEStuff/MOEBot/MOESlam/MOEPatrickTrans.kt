package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.*
import kotlin.math.cos
import kotlin.math.sin

class MOEPatrickTrans(val config: MOESlamConfig) {
    val initialCameraTrans = findInitialCameraTrans()
    fun findInitialCameraTrans(): Transformation {
        val radTheta = config.robotInitial.radAng + config.ROBOT_TO_CAMERA_DIST.radAng
        val pose = config.robotInitial.pose.getRelativePoint(config.ROBOT_TO_CAMERA_DIST.r, radTheta)
        val angle = (config.robotInitial.degAng + config.ROBOT_TO_CAMERA_THETA).toNormalAngle()
        return Transformation(pose, angle)
    }

    fun getCameraTrans(slamRawPose: Transformation): Transformation {
        val pose = findInitialCameraTrans().getPatrickAxesRotationOffset(slamRawPose.pose)
        val angle = (findInitialCameraTrans().degAng + slamRawPose.degAng).toNormalAngle()
        return Transformation(pose, angle)
    }

    fun getRobotTrans(slamPose: Transformation): Transformation {
        val radTheta = slamPose.radAng + config.ROBOT_TO_CAMERA_DIST.radAng
        val pose = slamPose.pose.getRelativePoint(config.ROBOT_TO_CAMERA_DIST.r, radTheta)
        val angle = (slamPose.degAng - config.ROBOT_TO_CAMERA_THETA).toNormalAngle()
        return Transformation(pose, angle)
    }

    fun getTrans(rawSlamTrans: Transformation): Transformation {
        val cameraTrans = getCameraTrans(rawSlamTrans)
        return getRobotTrans(cameraTrans)
    }

}

fun Transformation.getPatrickAxesRotationOffset(pointFromThis: Point): Point {

    val xOffset = pointFromThis.y * sin(this.radAng) + pointFromThis.x * cos(this.radAng)
    val yOffset = pointFromThis.y * cos(this.radAng) - pointFromThis.x * sin(this.radAng)
    return pointFromThis.create(this.pose.x + xOffset, this.pose.y + yOffset)
}

/*fun main() {
    val moePatrickTrans = MOEPatrickTrans(MOEPatrickSlamConfig(robotInitial = Transformation(10.0, 10.0,90.0)))
    val moePatrickRawCameraTrans = Transformation(20.0, -10.0, -90.0)
    val moePatrickCameraTrans = moePatrickTrans.getCameraTrans(moePatrickRawCameraTrans)

    println("Initial Camera Pose: " + moePatrickTrans.initialCameraTrans.toString())
    println("Camera Pose: " + moePatrickCameraTrans)
    println("Robot Pose: " + moePatrickTrans.getTrans(moePatrickCameraTrans))

}*/