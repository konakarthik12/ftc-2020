@file:Suppress("ArrayInDataClass")

package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity
import java.nio.ByteBuffer

object SlamData {

    @Volatile
    var curPose: FloatArray = FloatArray(3)
    @Volatile
    var quatAngle: DoubleArray = DoubleArray(4)
    @Volatile
    var lastTimestamp: Long = 0L
    val acceleration: FloatArray = FloatArray(3)
    val velocity: FloatArray = FloatArray(3)
    val angVelocity: FloatArray = FloatArray(3)
    val angAcceleration: FloatArray = FloatArray(3)
    @Volatile
    var confidence: Int = 0
    var delta: Long = 0L


    fun fillIn(order: ByteBuffer) {
        repeat(3) {
            curPose[it] = order.float
        }
        repeat(4) {
            quatAngle[it] = order.float.toDouble()
        }
        repeat(3) {
            velocity[it] = order.float
        }
        repeat(3) {
            angVelocity[it] = order.float
        }
        repeat(3) {
            acceleration[it] = order.float
        }
        repeat(3) {
            angAcceleration[it] = order.float
        }
        val newTimestamp = order.long
        delta = newTimestamp - lastTimestamp
        lastTimestamp = newTimestamp
        confidence = order.int
    }
}