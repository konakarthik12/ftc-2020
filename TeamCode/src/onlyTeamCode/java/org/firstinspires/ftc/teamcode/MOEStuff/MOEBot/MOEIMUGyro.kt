package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.hardware.bosch.BNO055IMU.AngleUnit
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap

class MOEIMUGyro : MOEGyro() {
    private val imu = hardwareMap.get(BNO055IMU::class.java, "imu")


    override fun getRawAngle() = (-imu.angularOrientation.firstAngle).toDouble()


    /**
     * Initializes IMU parameters
     */
    fun initialize() {
        val parameters = BNO055IMU.Parameters()
        parameters.angleUnit = AngleUnit.DEGREES
        imu.initialize(parameters)

    }


}