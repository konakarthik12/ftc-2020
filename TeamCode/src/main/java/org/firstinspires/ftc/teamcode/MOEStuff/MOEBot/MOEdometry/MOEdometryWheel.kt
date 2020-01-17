package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.OdometryConfig
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.WrapperHandler

class MOEDometryWheel(val config: OdometryConfig) {
    private val mMotor = config.motorConfig.getDevice()

    init {
        reset()
    }

    fun reset() {
        mMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
    }

    fun updateValue(angleWrapped: WrapperHandler): Double {
        return getScaledValue() - (angleWrapped.getValue() * config.turnCorrection)
    }
    fun getRawValue() = mMotor.currentPosition
    fun getScaledValue() = getRawValue() * config.scalar
}