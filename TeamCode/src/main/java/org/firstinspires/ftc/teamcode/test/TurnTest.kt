package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEFancyPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.toFixed

@TeleOp(name = "TurnTest")
class TurnTest : MOETeleOp() {


    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    override fun initOpMode() {
        //        telemetry.addData("testagain")
        //        robot.odometry.servos.initServosUp()
    }

    override fun loopStuff() {

        robot.chassis.turnPower(gamepad1.left_stick_x.toDouble())
    }

    //    private fun waitForGamepad() {
    //        while (g)
    //    }
}