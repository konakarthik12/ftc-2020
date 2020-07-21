package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOERobotInitialStateConfig
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.MOEtion
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@Disabled
@TeleOp
class RawOdometryTest : CompTeleOp() {
//    val sd_main = File(Environment.getExternalStorageDirectory().absolutePath + "/forward.txt")
//    val writer = sd_main.printWriter()


    override fun initOpMode() {
        super.initOpMode()
//        robot.odometry.launchLoop()
        telemetry.addData("test")
//        robot.odometry.servos.initServosDown()
    }

    override fun log() {

        val robotPose = robot.odometry.debugPose

        val angle = robot.gyro.angle

        val rightForwardValue = robot.odometry.rightForwardWheel.getValue()
        val strafe = robot.odometry.strafeWheel.getValue()
        val fieldCentricPose = robot.odometry.fieldCentricPose
//telemetry.addData("rawX",robot.odometry.)
        telemetry.addData("finalPose", robot.odometry.astarMoetion())
        telemetry.addData("forward", rightForwardValue)
        telemetry.addData("strafe", strafe)
        telemetry.addData("robot pc.getPose", robotPose)
        telemetry.addData("angle", angle)
        telemetry.addData("fieldcentric pc.getPose", fieldCentricPose)
//              sd_main.  .use { out -> out.println(fileContent) }
        telemetry.update()
//        writer.println("$angle\t$rightForwardValue\t$strafe")
    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply { useOdometry = true }
    }

    override fun getRobotInitialState(): MOERobotInitialStateConfig {
        return super.getRobotInitialState().apply {
            robotInitial = MOEtion(14.0, 96.0, 270.0)
        }
    }

}