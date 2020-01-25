package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.telemetry
import kotlin.math.abs

const val TICS_PER_INCH = 1323.7815 //tics per inch of encoder
val TICS_PER_ASTAR = TICS_PER_INCH * 2

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

    fun moveForwardAStars(astars: Double, power: Double=1.0, synchronous: Boolean = true) {

//        val position = (robot.odometry.rightForwardWheel.position + ticks * ASTARS_PER_TICK).toInt()
//        setTargetPositionAndPower(robot.odometry.rightForwardWheel.mMotor, position, power)
        robot.chassis.setPower(power)
        val wheel = robot.odometry.rightForwardWheel

        val final = wheel.getValue() + astars * TICS_PER_ASTAR
        while (wheel.getValue() < final && moeOpMode.iOpModeIsActive()) {
            telemetry.addData("cur astars", wheel.getValue())
            telemetry.addData("final astars", final)
            telemetry.update()
        }

        robot.chassis.stop()

    }

    fun moveBackwardAStars(astars: Double, power: Double, synchronous: Boolean = true) {
        robot.chassis.setPower(-power)
        val wheel = robot.odometry.rightForwardWheel

        val final = wheel.getValue() - astars * TICS_PER_ASTAR
        while (wheel.getValue() > final && moeOpMode.iOpModeIsActive()) {
            telemetry.addData("cur astars", wheel.getValue())
            telemetry.addData("final astars", final)
            telemetry.update()
        }

        robot.chassis.stop()
    }

    fun moveRightAstars(astars: Double, power: Double, synchronous: Boolean = true) {
        robot.chassis.setStrafePower(power)
        val wheel = robot.odometry.strafeWheel

        val final = wheel.getValue() + astars * TICS_PER_ASTAR
        while (wheel.getValue() < final && moeOpMode.iOpModeIsActive()) {
            telemetry.addData("cur astars", wheel.getValue())
            telemetry.addData("final astars", final)
            telemetry.update()
        }

        robot.chassis.stop()
    }

    fun moveLeftAstars(astars: Double, power: Double, synchronous: Boolean = true) {
        robot.chassis.setStrafePower(-power)
        val wheel = robot.odometry.strafeWheel

        val final = wheel.getValue() - astars * TICS_PER_ASTAR
        while (wheel.getValue() > final && moeOpMode.iOpModeIsActive()) {
            telemetry.addData("cur astars", wheel.getValue())
            telemetry.addData("final astars", final)
            telemetry.update()
        }

        robot.chassis.stop()
    }
}
