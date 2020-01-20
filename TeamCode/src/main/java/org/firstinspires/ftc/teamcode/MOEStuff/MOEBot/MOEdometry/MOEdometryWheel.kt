package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.OdometryConfig

class MOEDometryWheel(val config: OdometryConfig) {
     val mMotor = config.motorConfig.getDevice()
    private var offset = 0

    fun resetValues() {
        val oldMOde = mMotor.mode
        mMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        mMotor.mode = oldMOde

    }

//    fun resetValues() {
//    }

//    fun resetValues() {
//
//        offset = getRawValue()
//    }

    fun getFixedValue(angleWrapped: Double): Double {
        return getTurnCorrectedValue(angleWrapped) / config.scalar
    }

    fun getFixedAStars() = getFixedValue(0.0) * 2.0

    fun getRawValue() = mMotor.currentPosition * -1.0
    fun getTurnCorrectedValue(angleWrapped: Double) = getRawValue() - (angleWrapped * config.turnCorrection)
}
