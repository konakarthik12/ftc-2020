package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOETurnPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.MOEPidConstants

@TeleOp
class TPidforward : MOETeleOp() {
    var tPid = MOETurnPid(MOEPidConstants.tOptions)
    override fun mainLoop() {
        tPid.setpoint = { 0.0 }
        robot.chassis.setPower(Powers.fromMechanum(1.0, 0.0, tPid.getOutput(robot.gyro.angle)))
    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply { useSlam = true }
    }

}