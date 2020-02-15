package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class StrafeTest : MOERegularTest() {
    override fun run() {
        robot.chassis.encoders.moveLeftInches(48.0, 1.0)

    }


}