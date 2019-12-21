@file:Suppress("ArrayInDataClass")

package org.firstinspires.ftc.robotcontroller.moeglobal.slam

import java.nio.ByteBuffer

data class SlamData(
        var lastTimestamp: Long = 0L,
        val acceleration: FloatArray = FloatArray(3),
        val velocity: FloatArray = FloatArray(3),
        val angVelocity: FloatArray = FloatArray(3),
        val angAcceleration: FloatArray = FloatArray(3),
        var confidence: Int = 0
        ) {
    fun fillIn(order: ByteBuffer) {
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
        lastTimestamp = order.long
        confidence = order.int
    }
}