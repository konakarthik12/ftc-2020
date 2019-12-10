package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "RawOdometryTest")
class RawOdometryTest : MOETeleOp() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    override fun initOpMode() {
        telemetry.addData("test")
        robot.odometry.servos.initServosDown()
    }

    override fun mainLoop() {
        val arrayOf = arrayOf("LOAA12", "ROAA10", "ROSA11")
        arrayOf.forEach {
            val servo = hardwareMap.get(AnalogInput::class.java, it)
            telemetry.addData(servo.voltage.toString())
        }
        telemetry.addData(robot.odometry.position)
        telemetry.update()
    }
}