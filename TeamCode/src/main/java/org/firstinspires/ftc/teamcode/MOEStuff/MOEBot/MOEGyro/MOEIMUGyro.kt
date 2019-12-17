package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.hardware.bosch.BNO055IMU.AngleUnit
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.MOEConstants.GyroConfig
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.toEulerAngle

class MOEIMUGyro : MOEGyro() {
    private val imu = hardwareMap.get(BNO055IMU::class.java, "imu")
    private var initStarted = false

    override fun getRawAngle() = (-imu.angularOrientation.firstAngle).toDouble()
    override fun getRawEulerAngle(): Double = getRawAngle().toEulerAngle()

    /**
     * Initializes IMU parameters
     */
    override fun init(sync: Boolean) {

        if (!initStarted) {
            initStarted = true
            imu.initialize(GyroConfig.parameters)
        }
        if (sync) {
            waitForInit()
        }
    }

    private fun waitForInit() {
        while (!(moeOpMode.iIsStopRequested) || imu.isGyroCalibrated) {
        }
    }
}