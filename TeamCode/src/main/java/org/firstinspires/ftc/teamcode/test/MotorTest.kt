package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@TeleOp()
class MotorTest : MOETeleOp() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref
    }

    override fun initOpMode() {
        telemetry.addData("testagain")
    }

    override fun mainLoop() {

        val motor = robot.chassis.backRightMotor.mMotor
        telemetry.addData("hub", motor.controller.connectionInfo)
        telemetry.addData("connectionInfo", motor.connectionInfo)
        telemetry.addData("power", motor.power)
//        telemetry.addData("power", motor.motorType.)
        motor.power = gpad1.left.trigger()
        telemetry.update()
        robot.chassis.frontRightMotor.mMotor.power = gpad1.right.trigger()
//        hardwareMap.get()
    }
}