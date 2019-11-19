package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.hardware.bosch.BNO055IMU.AngleUnit
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import toEulerAngle

class MOEIMUGyro : MOEGyro() {
    private val imu = hardwareMap.get(BNO055IMU::class.java, "imu")
    private var initStarted = false;

    override fun getRawAngle() = (-imu.angularOrientation.firstAngle).toDouble()
    override fun getRawEulerAngle(): Double = getRawAngle().toEulerAngle()



    /**
     * Initializes IMU parameters
     */
    override fun init(sync: Boolean) {
        if (initStarted) {
            return;
        }
        initStarted = true

        val parameters = BNO055IMU.Parameters()
        parameters.angleUnit = AngleUnit.DEGREES
        imu.initialize(parameters)
        if (sync) {
            waitForInit()
        }
    }

    private fun waitForInit() {
        while (!(moeOpMode.isStopRequested||imu.isGyroCalibrated)) {

        }
    }


}