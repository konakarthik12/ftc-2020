package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton

@Disabled
@Autonomous
class StrafeTest : MOEAuton() {
    override fun run() {
        robot.chassis.encoders.moveLeftInches(48.0, 1.0)

    }


}