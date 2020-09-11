package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton

@Disabled
@Autonomous
class AutonOdometryTest : MOEAuton() {
    override fun run() {
        robot.chassis.encoders.moveForwardInches(48.0)
//        robot.chassis.encoders.moveBackwardInches(48.0)
        while (opModeIsActive()) {
        }
    }

}
