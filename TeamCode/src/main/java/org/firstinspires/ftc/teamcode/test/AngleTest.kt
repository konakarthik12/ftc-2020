package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toDegrees
import kotlin.math.sqrt

@TeleOp
class AngleTest : MOERegularTest() {
    override fun initOpMode() {
        robot.slam.restart()
    }

    override fun run() {

        while (opModeIsActive() && (robot.slam.pose.x < 160 && robot.slam.pose.y < 160)) {
            robot.chassis.setPower(Powers.fromAng(45.0, sqrt(2.0), 0.0))
        }
        while (opModeIsActive()) {
            robot.chassis.stop()
            telemetry.addData("slam2", robot.slam.pose)
            telemetry.addData("atan2", robot.slam.pose.atan2().toDegrees())
            telemetry.update()
        }

    }

}