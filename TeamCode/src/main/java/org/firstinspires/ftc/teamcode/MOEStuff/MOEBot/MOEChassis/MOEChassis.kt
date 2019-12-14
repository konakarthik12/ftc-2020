package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEFancyPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEtor
import org.firstinspires.ftc.teamcode.constants.MOEConstants.DriveTrain.Motors.Configs
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot

data class Powers(val FLP: Double, val FRP: Double, val BLP: Double, val BRP: Double)

class MOEChassis {
    val astar = MOEAstarSystem(this)

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