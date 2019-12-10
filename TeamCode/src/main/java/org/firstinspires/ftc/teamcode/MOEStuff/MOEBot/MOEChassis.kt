package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEFancyPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPid
import org.firstinspires.ftc.teamcode.constants.MOEConstants.DriveTrain.Motors.Configs
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.telemetry
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.wait

data class Powers(val FLP: Double, val FRP: Double, val BLP: Double, val BRP: Double)

class MOEChassis {
    var frontLeftMotor = MOEtor(Configs.FrontLeft)
    var frontRightMotor = MOEtor(Configs.FrontRight)
    var backLeftMotor = MOEtor(Configs.BackLeft)
    var backRightMotor = MOEtor(Configs.BackRight)
    val motors = listOf(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor)

    fun getFrontVelocities() = Pair(frontLeftMotor.getVelocity(), frontRightMotor.getVelocity())
    fun getBackVelocities() = Pair(backLeftMotor.getVelocity(), backRightMotor.getVelocity())

    fun setPower(P: Double) = setPower(P, P)
    fun setVelocity(V: Double) = setVelocity(V, V)

    fun setPower(LP: Double, RP: Double) = setPower(LP, RP, LP, RP)
    fun setVelocity(LV: Double, RV: Double) = setVelocity(LV, RV, LV, RV)

    fun setPower(powers: Powers) = setPower(powers.FLP, powers.FRP, powers.BLP, powers.BRP)

    fun setPower(FLP: Double, FRP: Double, BLP: Double, BRP: Double) {
        frontLeftMotor.setPower(FLP)
        frontRightMotor.setPower(FRP)
        backLeftMotor.setPower(BLP)
        backRightMotor.setPower(BRP)
    }

    fun setVelocity(FLV: Double, FRV: Double, BLV: Double, BRV: Double) {
        frontLeftMotor.setPower(FLV)
        frontRightMotor.setPower(FRV)
        backLeftMotor.setPower(BLV)
        backRightMotor.setPower(BRV)
    }

    fun turnPower(power: Double) = setPower(power, -power)

    fun turnRightPower(power: Double) = turnPower(power)
    fun turnRightLeft(power: Double) = turnPower(-power)

    fun moveAStars(astars: Double, direction: String = "Forward") {
        frontLeftMotor
    }

    fun jitter(astars: Double, power: Double) {
        moveForwardAStars(astars, power)
        moveBackwardAStars(astars, power)
    }

    fun moveForwardAStars(astars: Double, power: Double) {
        setPower(power)
        val currentPos = frontLeftMotor.distanceTraveled
        while ((frontLeftMotor.distanceTraveled - currentPos) < astars && moeOpMode.iOpModeIsActive()) {
            val currentPosition = robot.slam.getScaledRobotPose()
            moeOpMode.iTelemetry.addData(currentPosition)
            moeOpMode.iTelemetry.addData(frontLeftMotor.distanceTraveled)
            moeOpMode.iTelemetry.update()
        }
        stop()
    }

    fun moveForwardSlamAStars(astars: Double, power: Double) {
        setPower(power)
        val startPosition = robot.slam.getScaledRobotPose().y
        var currentPosition = startPosition
        while (-(currentPosition - startPosition) < astars && moeOpMode.iOpModeIsActive()) {
            currentPosition = robot.slam.getScaledRobotPose().y
            moeOpMode.iTelemetry.addData(currentPosition)
        }
        stop()
    }

    fun moveBackwardSlamAStars(astars: Double, power: Double) {
        setPower(-power)
        val startPosition = robot.slam.getScaledRobotPose().y
        var currentPosition = startPosition
        moeOpMode.iTelemetry.addData("current: ", currentPosition)
        moeOpMode.iTelemetry.addData("start: ", startPosition)
        moeOpMode.iTelemetry.addData("astars: ", astars)
        moeOpMode.iTelemetry.update()
        var et = ElapsedTime()
        et.reset()
        while ((currentPosition - startPosition) < astars && moeOpMode.iOpModeIsActive() && et.seconds() < 5000) {
            currentPosition = robot.slam.getScaledRobotPose().y
            moeOpMode.iTelemetry.addData("current: ", currentPosition)
            moeOpMode.iTelemetry.addData("start: ", startPosition)
            moeOpMode.iTelemetry.addData("astars: ", astars)
            moeOpMode.iTelemetry.update()
        }
        stop()
    }

    fun moveBackwardAStars(astars: Double, power: Double) {
        setPower(-power)
        val currentPos = frontLeftMotor.distanceTraveled
        while ((currentPos - frontLeftMotor.distanceTraveled) < astars && moeOpMode.iOpModeIsActive()) {
            moeOpMode.iTelemetry.addData(frontLeftMotor.distanceTraveled)
        }
        stop()
    }

    fun stop() {
        setPower(0.0)
    }

    fun turnTo(degrees: Double) {
        val pid = MOEFancyPid(1.0, 0.0, 0.0)
        pid.setOutputLimits(1.0)
        pid.setpoint = degrees
        pid.input = { robot.gyro.getRawAngle() }
        pid.output = { robot.chassis.turnPower(it) }
        pid.run()
        //        while (moeOpMode.opModeIsActive()) {
        //            val output = pid.getOutput(robot.gyro.getRawAngle())
        //            robot.chassis.turnPower(output)
        //            telemetry.addData(output)
        //            telemetry.update();
        //        }
    }

    fun moveTo(x: Double, y: Double) {
        val xPID = MOEFancyPid(1.0, 0.0, 0.0)
        xPID.setOutputLimits(1.0)
        xPID.setpoint = x
        xPID.input = { robot.slam.getRobotPose().x }
        xPID.output = { robot.chassis.turnPower(it) }
        xPID.run()
    }
}
