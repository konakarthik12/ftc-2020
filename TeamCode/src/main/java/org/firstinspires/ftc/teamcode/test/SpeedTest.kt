package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.teleop.PatrickTeleOp

@TeleOp
class SpeedTest : PatrickTeleOp() {


    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply { useOdometry = true }
    }

}