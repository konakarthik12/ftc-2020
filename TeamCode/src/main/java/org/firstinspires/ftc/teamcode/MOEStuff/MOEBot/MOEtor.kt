package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.MotorConfig
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.lerp


class MOEtor(val config: MotorConfig) {
    val error
        get() = mMotor.targetPosition - mMotor.currentPosition
    var mMotor = config.getDevice()
    //    private var powerScale = config.minPow..config.maxPow
    private val powRange = 0.0..config.maxPow

    init {
//        mMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
//        mMotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        setDirection(config.direction)
        setZeroPowerBehavior(config.zeroPowerBehavior)
    }

    val position: Int
        get() = mMotor.currentPosition

    val isBusy: Boolean
        get() = mMotor.isBusy

    val distanceTraveled: Double
        get() = position * MOEConstants.Units.ASTARS_PER_TICK

    //    fun setPosition() {
    //        mMotor.setPos
    //    }

    fun setPower(power: Double) {
        mMotor.power = (powRange).lerp(power)
    }

    fun setMode(mode: DcMotor.RunMode) {
        mMotor.mode = mode
    }

    fun setDirection(direction: Direction) {
        mMotor.direction = direction;
    }

    fun setZeroPowerBehavior(zeroPowerBehavior: ZeroPowerBehavior) {
        mMotor.zeroPowerBehavior = zeroPowerBehavior;
    }

    fun setVelocity(velocity: Double) = mMotor.setVelocity(velocity, AngleUnit.RADIANS)
    fun getVelocity() = mMotor.velocity
    fun resetEncoder() {
        val oldMOde = mMotor.mode
        mMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        mMotor.mode = oldMOde
    }

    fun setTargetPosition(position: Int) {
        mMotor.targetPosition = position
    }
}
