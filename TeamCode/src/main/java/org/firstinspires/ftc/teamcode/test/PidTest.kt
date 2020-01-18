package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEtion
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEAutonConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOERobotInitialStateConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.internal.get
import org.firstinspires.ftc.teamcode.utilities.internal.wait

@Autonomous
class PidTest : MOEAuton() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["turn"]
    }


    /*override fun initOpMode() {
        super.initOpMode()
        telemetry.addData("test:", "test")
        //robot.slam.restart()
    }*/


    override fun run() {
//        while (opModeIsActive() && !gamepad1.a) {
//            telemetry.addData("slamActualRaw", robot.slam.getRawPose())
//            telemetry.addData("slamOffset", robot.slam.slamOffset)
//            telemetry.addData("slamRaw", robot.slam.getRawOffsetPose())
//            telemetry.addData("slamRaw", robot.slam.getRawTrans())
//            telemetry.addData("pc.getPose", robot.slam.transformation)
//            telemetry.update()
//        }
        robot.chassis.moveTo(62.0, 96.0, 270.0)
        slamWait()
        robot.chassis.turnTo(0.0)
        slamWait()
        robot.chassis.moveTo(62.0, 240.0)
        slamWait()
        robot.foundation.closeServo()
        robot.chassis.stop()

        while (opModeIsActive()) {
            telemetry.addData("pc.getPose", robot.odometry.astarMoetion())
            telemetry.addData("pose", robot.odometry.astarMoetion())
            telemetry.update()
        }
    }

    private fun slamWait() {
        robot.chassis.stop()
        telemetry.addData("pc.getPose", robot.odometry.astarMoetion())
        telemetry.addData("pose", robot.odometry.astarMoetion())
        telemetry.update()
        wait(2000)
    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply { useOdometry = true }
    }

    override fun getAutonConfig(): MOEAutonConfig {
        return super.getAutonConfig()
    }

//    override fun getSlamConfig(): MOESlamConfig {
//        return super.getSlamConfig().apply {
//            ROBOT_TO_CAMERA_THETA = 180.0
//        }
//    }

    override fun getRobotInitialState(): MOERobotInitialStateConfig {
        return super.getRobotInitialState().apply {
            robotInitial = MOEtion(14.0, 96.0, 270.0)
        }
    }

}