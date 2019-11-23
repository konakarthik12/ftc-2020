package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPid
import org.firstinspires.ftc.teamcode.constants.MOEConstants.DriveTrain.Motors.Configs
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.telemetry
import org.firstinspires.ftc.teamcode.utilities.addData

class MOEChassis {
    private var frontLeftMotor = MOEtor(Configs.FrontLeft)
    private var frontRightMotor = MOEtor(Configs.FrontRight)
    private var backLeftMotor = MOEtor(Configs.BackLeft)
    private var backRightMotor = MOEtor(Configs.BackRight)
    private val motors = listOf(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor)

    fun getFrontVelocities() = Pair(frontLeftMotor.getVelocity(), frontRightMotor.getVelocity())
    fun getBackVelocities() = Pair(backLeftMotor.getVelocity(), backRightMotor.getVelocity())

    fun setPower(P: Double) = setPower(P, P)
    fun setVelocity(V: Double) = setVelocity(V, V)

    fun setPower(LP: Double, RP: Double) = setPower(LP, RP, LP, RP)
    fun setVelocity(LV: Double, RV: Double) = setVelocity(LV, RV, LV, RV)

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

    fun stop() {
        setPower(0.0)
    }

    fun turn(degrees: Double) {
        val pid = MOEPid(1.0, 0.0, 0.0)
        pid.setOutputLimits(0.0, 1.0)
        pid.setSetpoint(degrees)
        while (moeOpMode.opModeIsActive()) {
            val output = pid.getOutput(robot.gyro.getRawAngle())
            telemetry.addData(output)
            telemetry.update();
        }
    }


}
