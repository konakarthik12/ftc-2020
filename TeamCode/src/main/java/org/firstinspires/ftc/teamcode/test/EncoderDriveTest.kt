package org.firstinspires.ftc.teamcode.test

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER
import com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE
import com.qualcomm.robotcore.util.ElapsedTime


@TeleOp
class EncoderDriveTest : OpMode() {
    lateinit var motors: List<DcMotorEx>
    lateinit var frontLeft: DcMotorEx
    lateinit var frontRight: DcMotorEx
    lateinit var backLeft: DcMotorEx
    lateinit var backRight: DcMotorEx
    lateinit var multi: MultipleTelemetry
    override fun init() {
        multi = MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().telemetry)
        val voltage = hardwareMap.voltageSensor.first().voltage
        frontLeft = hardwareMap.dcMotor["FLDM10"] as DcMotorEx
        frontLeft.direction = REVERSE
        backLeft = hardwareMap.dcMotor["BLDM11"] as DcMotorEx
        backLeft.direction = REVERSE
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
            it.mode = STOP_AND_RESET_ENCODER
            it.mode = RUN_WITHOUT_ENCODER
        }

    }

    private val timer = ElapsedTime()
    override fun init_loop() {
        timer.reset()
    }

    val speed = 0.5

    override fun loop() {
        multi.addData("left", backLeft.currentPosition)
        multi.addData("right", frontRight.currentPosition )
        multi.addData("strafe", backRight.currentPosition )
        multi.update()
    }
}