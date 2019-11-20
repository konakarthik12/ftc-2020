package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam

import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamT265Handler
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Localization
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Units.ASTAR_PER_SLAM
import org.firstinspires.ftc.teamcode.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.external.AdvancedMath.Point3
import org.firstinspires.ftc.teamcode.external.AdvancedMath.toRadians
import org.firstinspires.ftc.teamcode.utilities.quaternionToHeading
import kotlin.math.cos
import kotlin.math.sin

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

    fun getRawOffsetPose(): Point3 {
        val rawPose = getRawPose()
        val mapped = rawPose.mapIndexed { index, it ->
            (it - slamOffset[index]) * ASTAR_PER_SLAM
        }
        return Point3(mapped)
    }

    fun getCameraPose(): Point {
        val angle = toRadians(getTheta())
        val rawPose = getRawOffsetPose()
        val x = rawPose.x * cos(angle) - rawPose.y * sin(angle)
        val y = rawPose.x * sin(angle) + rawPose.y * cos(angle)
        // flipped on purpose
        return Point(y, x)
    }

    fun getRobotPose(): Point {
        val theta = toRadians(getTheta() + Localization.CAMERA_THETA)
        val cameraX = cos(theta) * Localization.CAMERA_DISTANCE
        val cameraY = sin(theta) * Localization.CAMERA_DISTANCE

        val cameraPose = getCameraPose()
        return Point(cameraPose.x - cameraX, cameraPose.y - cameraY)
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