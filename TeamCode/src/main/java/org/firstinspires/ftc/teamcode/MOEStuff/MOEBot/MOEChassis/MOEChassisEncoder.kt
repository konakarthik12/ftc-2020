package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEtor
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Units.ASTARS_PER_TICK

class MOEChassisEncoder(val chassis: MOEChassis) {
    private fun waitThenStop(motor: MOEtor) {
        while (motor.isBusy) {}
        motor.setPower(0.0)
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER)
    }

    private fun setTargetPositionAndPower(motor: MOEtor, position: Int, power: Double) {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION)
        motor.setTargetPosition(position)
        motor.setPower(power)
    }

    fun moveForwardAStars(astars: Double, power: Double, synchronous: Boolean = true) {
        val position = (chassis.frontLeftMotor.position + astars * ASTARS_PER_TICK).toInt()
        setTargetPositionAndPower(chassis.frontLeftMotor, position, power)

        if (synchronous) {
            waitThenStop(chassis.frontLeftMotor)
        }
    }

    fun moveBackwardAStars(astars: Double, power: Double, synchronous: Boolean = true) {
        val position = (chassis.frontLeftMotor.position - astars * ASTARS_PER_TICK).toInt()
        setTargetPositionAndPower(chassis.frontLeftMotor, position, -power)

        if (synchronous) {
            waitThenStop(chassis.frontLeftMotor)
        }
    }
}
