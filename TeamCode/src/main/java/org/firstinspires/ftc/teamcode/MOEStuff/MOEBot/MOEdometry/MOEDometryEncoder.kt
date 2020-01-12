package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.OdometryConfig

class MOEDometryEncoder(config: OdometryConfig) {
    private val mMotor = config.getDevice()

    init {
        reset()
    }

    fun reset() {
        mMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
    }

    fun getValue() = mMotor.currentPosition
}