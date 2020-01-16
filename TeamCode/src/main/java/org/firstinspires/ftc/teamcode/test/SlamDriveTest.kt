package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOERohanSlamConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.PolarPoint

@TeleOp(name = "BSlamDriveTest")
class SlamDriveTest : CompTeleOp() {
    val laped = ElapsedTime()
    var diff: Int = 1000
    //    val printWriter = File(Environment.getExternalStorageDirectory().absolutePath + "/blank.txt").printWriter()

    override fun initOpMode() {
        super.initOpMode()
//        robot.slam.config = MOESlamConfig(0.0, 0.0, 0.0)
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
                robot.slam.resetValues()
                //                robot.slam.handler.re
//                robot.slam.checkConnection()
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
        telemetry.addData("ang", robot.gyro.angle)
        telemetry.addData("rawTrans", robot.slam.getRawTrans())
        val ij = robot.slam.transformation
        telemetry.addData("robottrans", ij)
//        telemetry.addData("")
//
//        telemetry.addData("timestamp", SlamData.lastTimestamp)
////        telemetry.addData("patrickPosition", robot.slam.transformation)
//        telemetry.addData("theta", robot.slam.getTheta().toPrecision(3))
//        val cameraPose = findCameraTrans(robot.slam.transHandler.cameraInitialTrans, robot.slam.getRawTrans()) //takes in 3 calculated constants, and 3 values (camera init pose and values from SLAM cam)
//        return findRobotTrans(cameraPose, config.cameraRelative)
//        telemetry.addData("patrickCameraPose", cameraPose)
//        telemetry.addData("robotaxis", robot.slam.getRobotPoseInCameraAxis())
        val raw = robot.slam.getRawPose().toString()
        //        printWriter.write(raw)
//        telemetry.addData("rawTrans", robot.slam.getRawTrans())
//        telemetry.addData("rawPoseInAstarts", robot.slam.getAstarPose())
//        telemetry.addData("rawPose", raw)
////        telemetry.addData("scaledPosition", robot.slam.getScaledRobotPose())
//        telemetry.addData("gyroAngle", robot.gyro.angle)
        telemetry.update()
        //        telemetry.addData("position", robot.slam.getRobotPose())
    }

    override fun getRobotConfig(): MOEBotConfig {
        return super.getRobotConfig().apply { useSlam = true }
    }

    override fun getSlamConfig(): MOESlamConfig {
        return super.getSlamConfig().apply {
            robotInitial = Transformation(14.0, 48.0, 270.0)
            ROBOT_TO_CAMERA_THETA = 180.0
        }
    }
}