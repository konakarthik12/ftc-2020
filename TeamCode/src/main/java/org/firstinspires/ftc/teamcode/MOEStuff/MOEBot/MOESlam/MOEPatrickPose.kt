
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.*
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

fun findCameraInitialTrans(robotInitial: Transformation, cameraRelative: PolarPoint): Transformation {
    val shift = robotInitial.degAng.toRadians() + cameraRelative.radAng
    val cameraInitialPose = robotInitial.pose.getRelativePoint(cameraRelative.r, robotInitial.radAng + cameraRelative.radAng)
    val cameraInitialAng = robotInitial.degAng + cameraRelative.degAng
    return Transformation(cameraInitialPose, cameraInitialAng)
}

fun findCameraTrans(cameraInitialTrans: Transformation, cameraRawTrans: Transformation): Transformation { //uses camera initial + camera raw values to calculate camera pose
    val r = cameraRawTrans.pose.hypot()
    val theta = (cameraInitialTrans.pose.atan2() + cameraInitialTrans.radAng).toDegrees()
    val cameraPose = cameraInitialTrans.pose.getRelativePoint(r, theta)
    val cameraAngle = cameraInitialTrans.degAng + cameraRawTrans.degAng
    return Transformation(cameraPose, cameraAngle)
}

fun findRobotTrans(cameraTransformation: Transformation, cameraRelative: PolarPoint): Transformation { //uses camera Pose + cameraRelative pose to calcculate robot pose
    val theta = cameraRelative.degAng + cameraTransformation.degAng
    val robotPose = cameraTransformation.pose.getRelativePoint(cameraRelative.r, theta)
    val robotAng = cameraTransformation.degAng - cameraRelative.degAng
    return Transformation(robotPose,robotAng)
    //    robotPose[0] = cameraX + cameraRelativeR * Math.sin(Math.toRadians(cameraAngle + cameraRelativeTheta))
    //    robotPose[1] = cameraY + cameraRelativeR * Math.cos(Math.toRadians(cameraAngle + cameraRelativeTheta))
    //    robotPose[2] = cameraAngle - cameraRelativeAngle
}


fun main(args: Array<String>) {
//    val cameraInitial = findCameraInitialTrans()
//    findCameraInitialTrans(robotInitialPose[0], robotInitialPose[1], robotInitialPose[2], cameraRelativePose[0], cameraRelativePose[1], cameraRelativePose[2]) //takes in 6 set constants (robot init pose and camera pose relateive to robot)
//    findCameraTrans(cameraInitialPose[0], cameraInitialPose[1], cameraInitialPose[2], rawPose[0], rawPose[1], rawPose[2]) //takes in 3 calculated constants, and 3 values (camera init pose and values from SLAM cam)
//    findRobotTrans(cameraPose[0], cameraPose[1], cameraPose[2], cameraRelativePose[0], cameraRelativePose[1], cameraRelativePose[2]) //takes in 3 calculated values and 3 constants (camera pose and camera pose relative to robot)
//    println("Camera Initial Pose: " + Arrays.toString(cameraInitialPose))
//    println("Raw Pose: " + Arrays.toString(rawPose))
//    println("Camera pose: " + Arrays.toString(cameraPose))
//    println("Robot Pose: " + Arrays.toString(robotPose))
}

