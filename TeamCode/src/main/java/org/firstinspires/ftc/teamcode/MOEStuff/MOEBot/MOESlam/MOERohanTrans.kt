package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.*

fun getCenterOffset(cameraToRobotDist: Double, robotToCameraTheta: Double): Point {
    val point = Point(0.0, 0.0).getRelativePoint(cameraToRobotDist, robotToCameraTheta)
    return -point
}


class MOERohanTrans(val config: MOESlamConfig) {
    val centerOffset = getCenterOffset(config.CAMERA_TO_ROBOT_DIST.r, config.CAMERA_TO_ROBOT_DIST.radAng)

    fun convertToFieldAxis(cameraRawTrans: Transformation): Transformation {
        val theta = config.CAMERA_TO_ROBOT_DIST.radAng + cameraRawTrans.radAng
        val robotInCameraAxis = cameraRawTrans.pose.getRelativePoint(config.CAMERA_TO_ROBOT_DIST.r, theta)

        val centeredPoint = robotInCameraAxis + centerOffset

        val cameraInRobotPoint = centeredPoint.rotateAroundOrigin(config.ROBOT_TO_CAMERA_THETA)

        val fieldDegrees = (config.robotInitial.radAng + cameraRawTrans.radAng).toDegrees()
        return Transformation(cameraInRobotPoint, fieldDegrees)
    }

    fun getTrans(slamRawPose: Transformation): Transformation {
        return convertToFieldAxis(slamRawPose)
    }
}