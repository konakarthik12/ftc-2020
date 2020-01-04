package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@TeleOp(name = "MotorTest")
class MotorTest : MOETeleOp() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref
    }

    override fun initOpMode() {
        telemetry.addData("testagain")
    }

    override fun mainLoop() {
        robot.chassis.setPower(FLP = 1.0, FRP = 0.0, BLP = 0.0, BRP = 0.0)
    }
}