package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton

@TeleOp(name = "PidTest")
class PidTest : MOEAuton() {
    override fun initOpMode() {

    }

    override fun run() {
        robot.chassis.turn(90.0);
    }
}
