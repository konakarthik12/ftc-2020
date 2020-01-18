package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam
//
//import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
//import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
//import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
//import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.*
//
//fun getCenterOffset(cameraToRobotDist: Double, robotToCameraTheta: Double): Point {
//    val point = Point(0.0, 0.0).getRelativePoint(cameraToRobotDist, robotToCameraTheta)
//    return -point
//}
//
//
//class MOERohanTrans(val config: MOESlamConfig) {
//    val centerOffset = getCenterOffset(config.CAMERA_TO_ROBOT_DIST.r, config.CAMERA_TO_ROBOT_DIST.radAng)
//
//    fun convertToFieldAxis(cameraRawTrans: Transformation): Transformation {
//        val theta = config.CAMERA_TO_ROBOT_DIST.radAng + cameraRawTrans.radAng
//        val robotInCameraAxis = cameraRawTrans.pc.getPose.getRelativePoint(config.CAMERA_TO_ROBOT_DIST.r, theta)
//
////        ReferenceHolder.telemetry.addData("theta", theta.toDegrees())
////        ReferenceHolder.telemetry.addData("robCamAxis", robotInCameraAxis)
//
//        val centeredPoint = robotInCameraAxis + centerOffset
//
////        ReferenceHolder.telemetry.addData("centerPoint", centeredPoint)
////        ReferenceHolder.telemetry.addData("robToCamtheta", config.ROBOT_TO_CAMERA_THETA)
//
//        val cameraInRobotPoint = centeredPoint.rotateAroundOrigin(config.ROBOT_TO_CAMERA_THETA.toRadians())
////        ReferenceHolder.telemetry.addData("camRobot", cameraInRobotPoint)
//        val offsetPoint = cameraInRobotPoint + config.robotInitial.pc.getPose
//        val fieldDegrees = (config.robotInitial.radAng + cameraRawTrans.radAng).toDegrees()
//        return Transformation(offsetPoint, fieldDegrees)
//    }
//
//    fun getTrans(slamRawPose: Transformation): Transformation {
//        return convertToFieldAxis(slamRawPose)
//    }
//}