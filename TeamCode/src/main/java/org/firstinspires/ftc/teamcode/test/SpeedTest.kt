package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toDegrees
import kotlin.math.sqrt

@TeleOp
class SpeedTest : CompTeleOp() {


    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply { useOdometry = true }
    }

}