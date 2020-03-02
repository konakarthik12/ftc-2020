package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled

@Disabled
@Autonomous
class StrafeTest : MOERegularTest() {
    override fun run() {
        robot.chassis.encoders.moveLeftInches(48.0, 1.0)

    }


}