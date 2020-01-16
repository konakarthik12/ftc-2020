package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEAutonConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.internal.get
import org.firstinspires.ftc.teamcode.utilities.internal.wait

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
        robot.chassis.moveTo(Transformation(62.0, 96.0, 270.0))
        slamWait()
        robot.chassis.turnTo(0.0)
        slamWait()
        robot.chassis.moveTo(Transformation(62.0, 240.0, 0.0))
        slamWait()
        robot.foundation.closeServo()
        robot.chassis.stop()

        while (opModeIsActive()) {
            telemetry.addData("pose", robot.slam.transformation)
            telemetry.update()
        }
    }

    private fun slamWait() {
        robot.chassis.stop()
        telemetry.addData("pose", robot.slam.transformation)
        telemetry.update()
        wait(2000)
    }

    override fun getRobotConfig(): MOEBotConfig {
        return super.getRobotConfig().apply { useSlam = true }
    }

    override fun getAutonConfig(): MOEAutonConfig {
        return super.getAutonConfig()
    }

    override fun getSlamConfig(): MOESlamConfig {
        return super.getSlamConfig().apply {
            robotInitial = Transformation(14.0, 96.0, 270.0)
            ROBOT_TO_CAMERA_THETA = 180.0
        }
    }

}