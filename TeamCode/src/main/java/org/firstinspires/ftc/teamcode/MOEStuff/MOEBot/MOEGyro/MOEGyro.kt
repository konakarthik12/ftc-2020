package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro

abstract class MOEGyro {
    private var eulerOffset = 0.0
    private var offset = 0.0
    val angle: Double
        get() = getRawAngle() + offset
    /**
     * Sets an offset for the IMU angle values
     * @param offset the offset to set
     */

    val radians: Double
        get() = Math.toRadians(getRawAngle())

    /**
     * Set the current angle as 0
     */
    fun setToZero() {
        offset = -getRawAngle()
    }

    /** 0 to 360*/
    abstract fun getRawAngle(): Double

    /** -179 to 180*/
    abstract fun getRawEulerAngle(): Double


    open fun init(sync: Boolean = false) {}


    var eulerAngle: Double
        get() {
            return getRawEulerAngle() - eulerOffset;
        }
        set(it) {
            eulerOffset = getRawEulerAngle() - it
        }

    fun resetEulerAngle() {
        eulerAngle = 0.0
    }
}