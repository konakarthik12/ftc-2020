package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOERobotInitialStateConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.MOEtion
import org.firstinspires.ftc.teamcode.utilities.internal.get

@Autonomous
class PidTest : MOEAuton() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["turn"]
    }


    override fun initOpMode() {
        super.initOpMode()
        telemetry.addData("test:", "test")

//        robot.slam.restart()
        telemetry.addData("pc.getPose", robot.odometry.astarMoetion())
        //robot.slam.restart()
    }


    override fun run() {
//        while (opModeIsActive() && !gamepad1.a) {
//            telemetry.addData("slamActualRaw", robot.slam.getRawPose())
//            telemetry.addData("slamOffset", robot.slam.slamOffset)
//            telemetry.addData("slamRaw", robot.slam.getRawOffsetPose())
//            telemetry.addData("slamRaw", robot.slam.getRawTrans())
//            telemetry.addData("pc.getPose", robot.slam.transformation)
//            telemetry.update()
//        }
//        robot.chassis.moveTo(62.0, 96.0, 270.0)
//        slamWait()
        robot.chassis.turnTo(0.0)
//        slamWait()
//        robot.chassis.moveTo(62.0, 240.0)
//        robot.chassis.turn(90.0)
        robot.foundation.moveDown()
        robot.chassis.stop()

        while (opModeIsActive()) {
            telemetry.addData("pc.getPose", robot.odometry.astarMoetion())
            telemetry.addData("pose", robot.odometry.astarMoetion())
            telemetry.addData("gyroType", robot.gyro::class.simpleName)
            telemetry.addData("gyro", robot.gyro.angle)
            telemetry.update()
        }
    }

//    private fun slamWait() {
//        robot.chassis.stop()
//        val angle = robot.gyro.angle
//        val rightForwardValue = robot.odometry.rightForwardWheel.getRawValue()
//        val strafe = robot.odometry.strafeWheel.getRawValue()
//        telemetry.addData("rightFoward", rightForwardValue)
//        telemetry.addData("strafe", strafe)
//        telemetry.addData("angle", angle)
//        telemetry.addData("pc.getPose", robot.odometry.astarMoetion())
//        telemetry.addData("pose", robot.odometry.astarMoetion())
//        telemetry.update()
//
//    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply {
            useOdometry = true
        }
    }

    override fun getRobotInitialState(): MOERobotInitialStateConfig {
        return MOERobotInitialStateConfig(
                MOEtion(14.0, 96.0, 270.0)
        )
    }

}