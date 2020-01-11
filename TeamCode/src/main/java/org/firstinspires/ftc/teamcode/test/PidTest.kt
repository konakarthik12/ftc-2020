package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEAutonConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.internal.get

@Autonomous
class PidTest : MOEAuton() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["turn"]
    }


    override fun initOpMode() {
        robot.slam.restart()

    }


    override fun run() {
        robot.chassis.moveTo(0.0, 100.0)
    }

    override fun getRobotConfig(): MOEBotConfig {
        return super.getRobotConfig().also { it.useSlam = true }
    }

    override fun getAutonConfig(): MOEAutonConfig {
        return super.getAutonConfig()
    }

}