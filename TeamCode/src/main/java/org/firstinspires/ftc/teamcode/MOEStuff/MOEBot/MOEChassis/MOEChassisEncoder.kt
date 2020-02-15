package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOETurnPid
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Units.TICS_PER_INCH
import org.firstinspires.ftc.teamcode.constants.MOEPidConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import kotlin.math.abs


val defaultForwardPower = 0.5
val defaultStrafePower = 0.5

class MOEChassisEncoder(val chassis: MOEChassis) {
    var tPid = MOETurnPid(MOEPidConstants.tOptions)

    private fun waitThenStop(motor: DcMotor, target: Double) {
        while (abs(motor.currentPosition - target) > 100)
            chassis.stop()
    }

    private fun setTargetPositionAndPower(motor: DcMotor, position: Int, power: Double) {
        while (motor.isBusy) {
        }
        motor.targetPosition = position
        motor.power = power
    }

    fun moveForwardInches(inches: Double, power: Double = defaultForwardPower, holdAngle: Double = robot.gyro.angle) {

//        val position = (robot.odometry.rightForwardWheel.position + ticks * INCHS_PER_TICK).toInt()
//        setTargetPositionAndPower(robot.odometry.rightForwardWheel.mMotor, position, power)
//        robot.chassis.setPower(power)
        val wheel = robot.odometry.rightForwardWheel

        val final = wheel.getValue() + inches * TICS_PER_INCH
        tPid.setpoint = { holdAngle }
        tPid.reset()
        while (wheel.getValue() < final && moeOpMode.iOpModeIsActive()) {
            val anglePower = tPid.getOutput(robot.gyro.angle)
            robot.chassis.setPower(Powers.fromMechanum(power, 0.0, anglePower))
//            telemetry.addData("cur inches", wheel.getValue())
//            telemetry.addData("final inches", final)
//            telemetry.update()
        }

        robot.chassis.stop()

    }

    fun moveBackwardInches(inches: Double, power: Double = defaultForwardPower, holdAngle: Double = robot.gyro.angle) {
        robot.chassis.setPower(-power)
        val wheel = robot.odometry.rightForwardWheel

        val final = wheel.getValue() - inches * TICS_PER_INCH
        tPid.setpoint = { holdAngle }
        tPid.reset()

        while (wheel.getValue() > final && moeOpMode.iOpModeIsActive()) {
            val anglePower = tPid.getOutput(robot.gyro.angle)
            robot.chassis.setPower(Powers.fromMechanum(-power, 0.0, anglePower))

//            telemetry.addData("cur inches", wheel.getValue())
//            telemetry.addData("final inches", final)
//            telemetry.update()
        }

        robot.chassis.stop()
    }

    fun moveRightInches(inches: Double, power: Double=0.5, synchronous: Boolean = true) {
        robot.chassis.setStrafePower(power)
        val wheel = robot.odometry.strafeWheel

        val final = wheel.getValue() + inches * TICS_PER_INCH
        while (wheel.getValue() < final && moeOpMode.iOpModeIsActive()) {
//            telemetry.addData("cur inches", wheel.getValue())
//            telemetry.addData("final inches", final)
//            telemetry.update()
        }

        robot.chassis.stop()
    }

    fun moveLeftInches(inches: Double, power: Double=0.5, synchronous: Boolean = true) {
        robot.chassis.setStrafePower(-power)
        val wheel = robot.odometry.strafeWheel

        val final = wheel.getValue() - inches * TICS_PER_INCH
        while (wheel.getValue() > final && moeOpMode.iOpModeIsActive()) {
//            telemetry.addData("cur inches", wheel.getValue())
//            telemetry.addData("final inches", final)
//            telemetry.update()
        }

        robot.chassis.stop()
    }


}
