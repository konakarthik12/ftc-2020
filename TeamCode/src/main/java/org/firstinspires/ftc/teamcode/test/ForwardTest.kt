package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig

@Autonomous
class ForwardTest : MOERegularTest() {
    override fun run() {
        robot.chassis.encoders.moveBackwardInches(96.0, 0.5)
        while (opModeIsActive()) {
            telemetry.addData("gyro", robot.gyro.angle)
            telemetry.update()
        }
    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply { useSlam = true }
    }
}