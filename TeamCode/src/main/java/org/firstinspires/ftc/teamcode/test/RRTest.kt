package org.firstinspires.ftc.teamcode.test

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.LEFT_SCALAR
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.RIGHT_SCALAR
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.STRAFE_SCALAR

@TeleOp
class RRTest : OpMode() {
    lateinit var motors: List<DcMotorEx>
    lateinit var frontLeft: DcMotorEx
    lateinit var frontRight: DcMotorEx
    lateinit var backLeft: DcMotorEx
    lateinit var backRight: DcMotorEx
    lateinit var multi: MultipleTelemetry
    lateinit var localizer: StandardTrackingWheelLocalizer
    override fun init() {
        multi = MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().telemetry)
        val voltage = hardwareMap.voltageSensor.first().voltage
        frontLeft = hardwareMap.dcMotor["FLDM10"] as DcMotorEx
        frontLeft.direction = DcMotorSimple.Direction.REVERSE
        backLeft = hardwareMap.dcMotor["BLDM11"] as DcMotorEx
        backLeft.direction = DcMotorSimple.Direction.REVERSE
        frontRight = hardwareMap.dcMotor["FRDM12"] as DcMotorEx
        backRight = hardwareMap.dcMotor["BRDM13"] as DcMotorEx

        multi.addData("voltage", voltage)
        multi.update()
        motors = listOf(
                frontLeft,
                frontRight,
                backLeft,
                backRight
        )
        motors.forEach {
            it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
            it.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        }
        val localizer = StandardTrackingWheelLocalizer(backLeft, frontRight, backRight)

    }

    override fun loop() {
        localizer.update()
        multi.addData("x", localizer.poseEstimate.x)
        multi.addData("y", localizer.poseEstimate.y)
        multi.addData("theta", Math.toDegrees(localizer.poseEstimate.heading))
        multi.addData("left", backLeft.currentPosition)
        multi.addData("right", frontRight.currentPosition)
        multi.addData("strafe", backRight.currentPosition)

        multi.update()


    }

}

class StandardTrackingWheelLocalizer(val left: DcMotor, val right: DcMotor, val strafe: DcMotor) : ThreeTrackingWheelLocalizer(listOf(
        Pose2d(-0.417, 7.834), // left parallel
        Pose2d(7.834, -0.417), // right parallel
        Pose2d(0.343, -0.276, Math.toRadians(90.0)) // perpendicular
)) {
    override fun getWheelPositions(): List<Double> {
        return listOf(
                left.currentPosition / LEFT_SCALAR,
                right.currentPosition / RIGHT_SCALAR,
                strafe.currentPosition / STRAFE_SCALAR
        )
        // return list of encoder readings in inches
    }
}