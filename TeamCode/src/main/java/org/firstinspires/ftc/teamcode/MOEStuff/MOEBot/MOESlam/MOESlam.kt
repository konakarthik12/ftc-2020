package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam

import android.util.Log
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamData
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamT265Handler
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Units.ASTARS_PER_METER
import org.firstinspires.ftc.teamcode.utilities.external.quaternionToHeading
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.telemetry
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.*

class MOESlam(var config: MOESlamConfig) {
    val transformation: Transformation
        get() = getTrans()

    val transHandler = MOERohanTrans(config)

    val handler: SlamT265Handler?
        get() = SlamHandler.t265Handler
    var slamOffset: Point = Point(0.0, 0.0)
    var thetaOffset: Double = 0.0

//    init {
//        resetValues()
//    }

    fun checkConnection() {
        //        handler?.killStream()
        handler?.startStream()
    }

    fun getRawOffsetPose(): Point = getRawPose() - slamOffset

    fun getAstarPose() = getRawOffsetPose() * ASTARS_PER_METER

    fun getTheta(): Double = -(getRawTheta().toEulerAngle() - thetaOffset)

    fun getRawTheta(): Double = quaternionToHeading(getQuadTheta())

    fun getRawPose(): Point {
        // The y is flipped to make forward movement positive on the camera.
        return Point(SlamData.curPose[0].toDouble(), -SlamData.curPose[2].toDouble())
    }

    fun resetValues() {
        setOffset(getRawPose())
        this.thetaOffset = -getRawTheta()
    }

    private fun setOffset(curPose: Point) {
        this.slamOffset = Point(curPose.x, curPose.y)
    }

    fun restart() {
        Log.e("restart", "started")
        Log.e("datas", SlamData.quatAngle.contentToString())
        handler?.restart()
        var last = SlamData.lastTimestamp
        val timer = ElapsedTime().apply { seconds() }
        while (!moeOpMode.iIsStopRequested && SlamData.lastTimestamp != 0L) {
            if (last != SlamData.lastTimestamp) {
                last = SlamData.lastTimestamp
                timer.reset()
            }
            if (timer.seconds() > 2.5) {
                break
            }
        }
        checkConnection()
        waitForData()
        Log.e("restart", "done")
        Log.e("datae", SlamData.quatAngle.contentToString())
    }

    private fun getQuadTheta(): DoubleArray = SlamData.quatAngle
    fun waitForData() {
        while (!moeOpMode.iIsStopRequested && SlamData.lastTimestamp == 0L) {
            telemetry.update()
        }
    }

    private fun getTrans(): Transformation {
        return transHandler.getTrans(getRawTrans())
    }

    fun getRawTrans(): Transformation {
        return Transformation(getAstarPose(), getTheta())
    }
}
