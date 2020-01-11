package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Lift.Motors

class MOELift {
    val leftMotor = MOEtor(Motors.LeftLiftMotor)
    val rightMotor = MOEtor(Motors.RightLiftMotor)
    val motors = arrayOf(leftMotor, rightMotor)
    fun setPower(power: Double) {
        motors.forEach { it.setPower(power) }
    }

    fun setVelocity(velocity: Double) {
        motors.forEach { it.setVelocity(velocity) }
    }

    fun getVelocities(): String {
        return motors.joinToString { it.getVelocity().toString() }
    }

    fun resetEncoders() {
        motors.forEach { it.resetEncoder() }
    }

    fun getPositions(): String? {
        return motors.joinToString { it.position.toString() }
    }

    fun setTargetPosition(target: Int) {
        motors.forEach { it.setTargetPosition(target) }
    }

    fun setRunWithoutEncoder() {
        motors.forEach { it.mMotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER }
    }

    fun setRunToPosition() {

        motors.forEach { it.mMotor.mode = DcMotor.RunMode.RUN_TO_POSITION }


    }

    fun getPowers(): String? {
        return motors.joinToString { it.mMotor.power.toString() }
    }

}
