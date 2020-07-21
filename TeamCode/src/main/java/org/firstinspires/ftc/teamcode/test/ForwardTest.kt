package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton

@Disabled

@Autonomous
class ForwardTest : MOEAuton() {
    override fun run() {
        robot.chassis.encoders.moveForwardInches(50.0)

    }


}