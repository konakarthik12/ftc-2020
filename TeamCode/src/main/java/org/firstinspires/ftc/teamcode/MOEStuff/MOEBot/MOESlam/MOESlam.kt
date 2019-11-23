package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam

import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamT265Handler
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Localization
import org.firstinspires.ftc.teamcode.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.external.AdvancedMath.toRadians
import org.firstinspires.ftc.teamcode.utilities.quaternionToHeading

class MOESlam {
    lateinit var handler: SlamT265Handler
    var slamOffset: FloatArray = floatArrayOf(0.0F, 0.0F, 0.0F)
    var thetaOffset: Double = 0.0;

    init {
        checkConnection()
    }

    private fun checkConnection() {
        val t265Handler = SlamHandler.t265Handler
        handler = t265Handler
        handler.killStream()
        handler.startStream()
    }

    fun getRawOffsetPose(): Point = getRawPose().let {raw->
        return Point(raw[0] - slamOffset[0].toDouble(), raw[1] - slamOffset[1].toDouble())
    }

    fun getCameraPose(): Point {
        val angle = toRadians(getTheta())
        val rawPose = getRawOffsetPose()
        return rawPose.rotateAroundOrigin(angle)
        //        with(rawPose) {
        //

        //            // flipped on purpose
        //            return Point(y, x)
        //        }
    }

    fun getRobotPose(): Point {
        val theta = toRadians(getTheta() + Localization.CAMERA_THETA)
        return getCameraPose().getRelativePoint(Localization.CAMERA_DISTANCE, theta)
    }

    fun getTheta(): Double = (getRawTheta() - thetaOffset)

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

    fun getQuadTheta(): DoubleArray = handler.quatAngle
}