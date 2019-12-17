package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEFancyPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.toFixed

@TeleOp(name = "StrafeTest")
class StrafeTest : MOETeleOp() {


    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    override fun initOpMode() {
        //        telemetry.addData("testagain")
        //        robot.odometry.servos.initServosUp()
    }

    override fun mainLoop() {
        //        val i = 20;
        val str = 20.0
        robot.chassis.setVelocity(str)
        telemetry.addData(robot.chassis.getVelocities())
    }

    //    private fun waitForGamepad() {
    //        while (g)
    //    }
}