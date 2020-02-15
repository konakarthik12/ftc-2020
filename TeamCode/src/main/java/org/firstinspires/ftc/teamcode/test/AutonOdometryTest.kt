package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class AutonOdometryTest : MOERegularTest() {
    override fun run() {
        robot.chassis.encoders.moveForwardInches(48.0)
//        robot.chassis.encoders.moveBackwardInches(48.0)
        while (opModeIsActive()) {
        }
    }

}
