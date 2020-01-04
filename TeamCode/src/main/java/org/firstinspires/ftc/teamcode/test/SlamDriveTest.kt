package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamData
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.external.toPrecision

@TeleOp(name = "BSlamDriveTest")
class SlamDriveTest : CompTeleOp() {
    val laped = ElapsedTime()
    var diff: Int = 1000
    //    val printWriter = File(Environment.getExternalStorageDirectory().absolutePath + "/blank.txt").printWriter()

    override fun initOpMode() {
        super.initOpMode()
        robot.slam.config = MOESlamConfig(0.0, 0.0, 0.0)
        Log.e("why", "yessir")
        //        gpad1.a.onKeyDown {
        //            Log.e("restarting", "yes")
        //            SlamHandler.t265Handler?.commands?.addLast(Constants.DEV_STOP)
        //        }
        //        gpad1.b.onKeyDown {
        //            Log.e("starting", "yes")
        //
        //            SlamHandler.t265Handler?.commands?.addLast(Constants.SLAM_CONTROL)
        //            SlamHandler.t265Handler?.commands?.addLast(Constants.POSE_CONTROL)
        //            SlamHandler.t265Handler?.commands?.addLast(Constants.DEV_START)
        //        }
        gpad1.y.onKeyDown {
            Thread(Runnable {
                //                robot.slam.handler.re
                robot.slam.checkConnection()
            }).start()
        }
        gpad1.x.onKeyDown {
            Thread(Runnable {
                //                robot.slam.handler.re
                robot.slam.restart()
            }).start()
        }
    }

    override fun log() {
        //        super.mainLoop()
        //        if (laped.milliseconds().toInt() > diff) {
        //            val robotPose = robot.slam.getRawPose()
        //            MOESocketHandler.moeWebServer.broadcast("data/slam/${robotPose[0]},${robotPose[1]},${robot.gyro.getRawAngle()}")
        //            laped.reset()
        //        }
        telemetry.addData("timestamp", SlamData.lastTimestamp)
        telemetry.addData("theta", robot.slam.getTheta().toPrecision(3))
        val raw = robot.slam.getRawPose().contentToString()
        telemetry.addData("cameraPositionAstars", robot.slam.getCameraPose() * MOEConstants.Units.ASTARS_PER_METER)
        telemetry.addData("rawPose", raw)
        //        printWriter.write(raw)
        telemetry.addData("rawPoseInCM", robot.slam.getRawPose().map { it * 100 })
        telemetry.addData("rawPoseOffset", robot.slam.getRawOffsetPose())
        telemetry.addData("robotaxis", robot.slam.getRobotPoseInCameraAxis() * MOEConstants.Units.ASTARS_PER_METER)
        telemetry.addData("scaledPosition", robot.slam.getScaledRobotPose())
        telemetry.addData("gyroAngle", robot.gyro.angle)
        telemetry.update()
        //        telemetry.addData("position", robot.slam.getRobotPose())
    }

    override fun getRobotConfig(): MOEBotConfig {
        return super.getRobotConfig().also { it.useSlam = true }
    }
}