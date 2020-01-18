package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEGyroConfig
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toNormalAngle
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toRadians

abstract class MOEGyro {
    var config = MOEGyroConfig()
        set(value) {
            field = value
            setInitialAngle(value.initalAng)
//            offset = getRawAngle() - value.initalAng
        }
    var offset = 0.0
    private var eulerOffset = 0.0
    val angle: Double
        get() = (getRawAngle() + offset).toNormalAngle()
    /**
     * Sets an offset for the IMU angle values
     * @param offset the offset to set
     */

    val radAng: Double
        get() = angle.toRadians()

    /**
     * Set the current angle as 0
     */
    fun setToZero() {
        setInitialAngle(0.0)
    }

    private fun setInitialAngle(initialAng: Double) {
        offset = initialAng - getRawAngle()
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