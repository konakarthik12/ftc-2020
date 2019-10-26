package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

abstract class MOEGyro {
    private var offset: Double = 0.0
    val angle: Double
        get() = getRawAngle() + offset
    /**
     * Sets an offset for the IMU angle values
     * @param offset the offset to set
     */


    /**
     * Set the current angle as 0
     */
    fun setToZero() {
        offset = -getRawAngle()
    }

    abstract fun getRawAngle(): Double

}