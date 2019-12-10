package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode

data class MotorConfig(val name: String, val direction: Direction = Direction.FORWARD,
                       val zeroPowerBehavior: ZeroPowerBehavior = BRAKE)


class MOEtor(config: MotorConfig) {
    private var mMotor: DcMotorEx = moeOpMode.iHardwareMap.get(
            DcMotorEx::class.java, config.name)

    init {
        mMotor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        mMotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        setDirection(config.direction)
        setZeroPowerBehavior(config.zeroPowerBehavior)
    }

    val position: Int
        get() = mMotor.currentPosition

    val distanceTraveled: Double
        get() = position * MOEConstants.Units.ASTARS_PER_TICK

//    fun setPosition() {
//        mMotor.setPos
//    }

    fun setPower(power: Double) {
        mMotor.power = power;
    }

    fun setDirection(direction: Direction) {
        mMotor.direction = direction;
    }

    fun setZeroPowerBehavior(zeroPowerBehavior: ZeroPowerBehavior) {
        mMotor.zeroPowerBehavior = zeroPowerBehavior;
    }

    fun getVelocity() = mMotor.getVelocity()  // TODO: Check if this is in ticks or angles.
}
