package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.constants.MOEConstants.Lift.Motors

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

    fun setPosition(target: Int) {
        motors.forEach { it.setTargetPosition(target) }
    }

}
