package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

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

    abstract fun getRawAngle(): Double
    abstract fun init(sync: Boolean = false)


    var eulerAngle: Double
        get() {
            return getRawEulerAngle() - eulerOffset;
        }
        set(it) {
            eulerOffset = getRawEulerAngle() - it
        }


    private fun getRawEulerAngle(): Double {
        var eulerAng = -getRawAngle()
        if (eulerAng > 180) {
            eulerAng = -(180 + (180 - eulerAng))
        } else if (eulerAng < -180) {
            eulerAng += 360
        }
        return eulerAng
    }

    fun resetEulerAngle() {
        eulerAngle = 0.0
    }
}