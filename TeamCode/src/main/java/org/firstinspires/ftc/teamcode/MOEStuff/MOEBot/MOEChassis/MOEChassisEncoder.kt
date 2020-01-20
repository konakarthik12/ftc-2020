package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEtor
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Units.ASTARS_PER_TICK
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.telemetry
import kotlin.math.abs

class MOEChassisEncoder(val chassis: MOEChassis) {
    private fun waitThenStop(motor: DcMotor, target: Double) {
        while (abs(motor.currentPosition - target) > 100)
            chassis.stop()
    }

    private fun setTargetPositionAndPower(motor: DcMotor, position: Int, power: Double) {
        while (motor.isBusy) {
        }
        motor.setTargetPosition(position)
        motor.setPower(power)
    }

    fun moveForwardAStars(astars: Double, power: Double, synchronous: Boolean = true) {
//        val position = (robot.odometry.rightForwardWheel.position + ticks * ASTARS_PER_TICK).toInt()
//        setTargetPositionAndPower(robot.odometry.rightForwardWheel.mMotor, position, power)
        robot.chassis.setPower(power)
        val wheel = robot.odometry.rightForwardWheel

        val final = wheel.getFixedAStars() + astars
        while (wheel.getFixedAStars() < final && moeOpMode.iOpModeIsActive()) {
            telemetry.addData("cur astars", wheel.getFixedAStars())
            telemetry.addData("final astars", final)
            telemetry.update()
        }

        robot.chassis.stop()

    }

    fun moveBackwardAStars(astars: Double, power: Double, synchronous: Boolean = true) {
        robot.chassis.setPower(-power)
        val wheel = robot.odometry.rightForwardWheel

        val final = wheel.getFixedAStars() - astars
        while (wheel.getFixedAStars() < final && moeOpMode.iOpModeIsActive()) {
            telemetry.addData("cur astars", wheel.getFixedAStars())
            telemetry.addData("final astars", final)
            telemetry.update()
        }

        robot.chassis.stop()
    }
}
