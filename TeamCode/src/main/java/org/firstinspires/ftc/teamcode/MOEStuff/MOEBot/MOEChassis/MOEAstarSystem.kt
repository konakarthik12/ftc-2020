package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.utilities.addData

class MOEAstarSystem(val chassis: MOEChassis) {
    fun moveAStars(astars: Double, direction: String = "Forward") {
        //    frontLeftMotor
    }

    fun jitter(astars: Double, power: Double) {
        moveForwardAStars(astars, power)
        moveBackwardAStars(astars, power)
    }

    fun moveForwardAStars(astars: Double, power: Double) {
        chassis.setPower(power)
        val currentPos = chassis.frontLeftMotor.distanceTraveled
        while ((chassis.frontLeftMotor.distanceTraveled - currentPos) < astars && ReferenceHolder.moeOpMode.iOpModeIsActive()) {
            val currentPosition = ReferenceHolder.robot.slam.getScaledRobotPose()
            ReferenceHolder.moeOpMode.iTelemetry.addData(currentPosition)
            ReferenceHolder.moeOpMode.iTelemetry.addData(chassis.frontLeftMotor.distanceTraveled)
            ReferenceHolder.moeOpMode.iTelemetry.update()
        }
        chassis.stop()
    }

    fun moveForwardSlamAStars(astars: Double, power: Double) {
        chassis.setPower(power)
        val startPosition = ReferenceHolder.robot.slam.getScaledRobotPose().y
        var currentPosition = startPosition
        while (-(currentPosition - startPosition) < astars && ReferenceHolder.moeOpMode.iOpModeIsActive()) {
            currentPosition = ReferenceHolder.robot.slam.getScaledRobotPose().y
            ReferenceHolder.moeOpMode.iTelemetry.addData(currentPosition)
        }
        chassis.stop()
    }

    fun moveBackwardSlamAStars(astars: Double, power: Double) {
        chassis.setPower(-power)
        val startPosition = ReferenceHolder.robot.slam.getScaledRobotPose().y
        var currentPosition = startPosition
        ReferenceHolder.moeOpMode.iTelemetry.addData("current: ", currentPosition)
        ReferenceHolder.moeOpMode.iTelemetry.addData("start: ", startPosition)
        ReferenceHolder.moeOpMode.iTelemetry.addData("astars: ", astars)
        ReferenceHolder.moeOpMode.iTelemetry.update()
        var et = ElapsedTime()
        et.reset()
        while ((currentPosition - startPosition) < astars && ReferenceHolder.moeOpMode.iOpModeIsActive() && et.seconds() < 5000) {
            currentPosition = ReferenceHolder.robot.slam.getScaledRobotPose().y
            ReferenceHolder.moeOpMode.iTelemetry.addData("current: ", currentPosition)
            ReferenceHolder.moeOpMode.iTelemetry.addData("start: ", startPosition)
            ReferenceHolder.moeOpMode.iTelemetry.addData("astars: ", astars)
            ReferenceHolder.moeOpMode.iTelemetry.update()
        }
        chassis.stop()
    }

    fun moveBackwardAStars(astars: Double, power: Double) {
        chassis.setPower(-power)
        val currentPos = chassis.frontLeftMotor.distanceTraveled
        while ((currentPos - chassis.frontLeftMotor.distanceTraveled) < astars && ReferenceHolder.moeOpMode.iOpModeIsActive()) {
            ReferenceHolder.moeOpMode.iTelemetry.addData(chassis.frontLeftMotor.distanceTraveled)
        }
        chassis.stop()
    }
}