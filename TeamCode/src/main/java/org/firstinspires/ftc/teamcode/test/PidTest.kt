package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEAutonConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOERohanSlamConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
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
//        while (opModeIsActive() && !gamepad1.a) {
//            telemetry.addData("slamActualRaw", robot.slam.getRawPose())
//            telemetry.addData("slamOffset", robot.slam.slamOffset)
//            telemetry.addData("slamRaw", robot.slam.getRawOffsetPose())
//            telemetry.addData("slamRaw", robot.slam.getRawTrans())
//            telemetry.addData("pose", robot.slam.transformation)
//            telemetry.update()
//        }
        val turn = robot.chassis.moveTo(Transformation(95.0, 0.0, 0.0))

        while (opModeIsActive() && turn.isActive) {
            telemetry.addData("pose", robot.slam.getRawTrans())
            telemetry.addData("pose", robot.slam.transformation)
            telemetry.update()
        }
        robot.chassis.stop()
    }

    override fun getRobotConfig(): MOEBotConfig {
        return super.getRobotConfig().apply { useSlam = true }
    }

    override fun getAutonConfig(): MOEAutonConfig {
        return super.getAutonConfig()
    }

    override fun getSlamConfig(): MOESlamConfig {
        return super.getSlamConfig().apply {
            robotInitial = Transformation(11.0, 48.0, -90.0)
            ROBOT_TO_CAMERA_THETA = 180.0
        }
    }

}