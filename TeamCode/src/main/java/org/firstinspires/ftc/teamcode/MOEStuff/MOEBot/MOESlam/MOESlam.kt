package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam

import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamT265Handler
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.MOEConstants.SLAM
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.toRadians
import org.firstinspires.ftc.teamcode.utilities.quaternionToHeading
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.toNormalAngle
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode

data class MOESlamOptions(val robotToFieldTheta: Double, val xOffset: Double, val yOffset: Double)

class MOESlam(val setInitialOffsets: Boolean = false) {
    lateinit var options: MOESlamOptions

    constructor(options: MOESlamOptions) : this() {
        this.options = options
    }
    //
    //    private fun setOptions(options: MOESlamOptions) {
    //        this.options = options;
    //    }

    lateinit var handler: SlamT265Handler
    var slamOffset: FloatArray = floatArrayOf(0.0F, 0.0F, 0.0F)
    var thetaOffset: Double = 0.0

    init {
        checkConnection()

        if (setInitialOffsets) {
            setInitialOffsets()
        }
    }

    fun setOptions(thetaInDegrees: Double, xOffset: Double, yOffset: Double) {
        options = MOESlamOptions(toRadians(thetaInDegrees), xOffset, yOffset)
    }

    private fun checkConnection() {
        val t265Handler = SlamHandler.t265Handler
        handler = t265Handler
        handler.killStream()
        handler.startStream()
    }

    fun getRawOffsetPose(): Point = getRawPose().let {
        return Point(it[0] - slamOffset[0].toDouble(), it[2] - slamOffset[2].toDouble())
    }

    fun getCameraPose(): Point {
        val rawPose = getRawOffsetPose()
        return Point(rawPose.x, -rawPose.y)
    }

    fun getRobotPoseInCameraAxis(): Point {
        return getCameraPose().getRelativePoint(
                distanceFromThis = SLAM.CAMERA_DISTANCE,
                theta = toRadians(getTheta() + SLAM.INITIAL_CAMERA_THETA)
        )
        //        rawPose.getRelativePoint(Localization.CAMERA_DISTANCE, Localization.)
        //        return rawPose.rotateAroundOrigin(angle)

        //        val theta = toRadians(getTheta() + Localization.CAMERA_THETA)
        //        return getCameraPose().getRelativePointLocalization.CAMERA_DISTANCE, theta)
    }

    fun getRobotPose(): Point {
        val untranslatedPose = getRobotPoseInCameraAxis().rotateAroundOrigin(options.robotToFieldTheta)
        return Point(untranslatedPose.x + options.xOffset, untranslatedPose.y + options.yOffset)
    }

    fun getScaledRobotPose(): Point = getRobotPose() * MOEConstants.Units.ASTARS_PER_METER
    //
    //    fun getRawEulerTheta(): Double {
    //
    //    }

    fun getTheta(): Double = (getRawTheta().toNormalAngle() - thetaOffset)

    fun getRawTheta(): Double = quaternionToHeading(getQuadTheta())

    fun getRawPose() = handler.curPose!!

    override fun toString(): String {
        return getCameraPose().toString()
    }

    fun resetValues(thetaOffset: Double) {
        setOffset(handler.curPose)
        this.thetaOffset = thetaOffset
    }

    private fun setOffset(curPose: FloatArray) {
        this.slamOffset = curPose.copyOf()
    }

    private fun setInitialOffsets() {
        val point = Point(0.0, 0.0).getRelativePoint(
                distanceFromThis = SLAM.CAMERA_DISTANCE,
                theta = toRadians(getTheta() + SLAM.INITIAL_CAMERA_THETA)
        );
        setOffset(floatArrayOf(point.x.toFloat(), point.y.toFloat()))
    };
    fun restart() = handler.restart()

    fun getQuadTheta(): DoubleArray = handler.quatAngle
}
