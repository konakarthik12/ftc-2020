package org.firstinspires.ftc.teamcode.test

import android.os.Environment
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOESocketHandler
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlamOptions
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.teleop.DuoDrive
import org.firstinspires.ftc.teamcode.utilities.toFixed
import java.io.File

@TeleOp(name = "SlamDriveTest")
class SlamDriveTest : DuoDrive(usingSlam = true) {
    val laped = ElapsedTime()
    var diff: Int = 1000
    val printWriter = File(Environment.getExternalStorageDirectory().absolutePath+"/blank.txt").printWriter()
    override fun initOpMode() {
        super.initOpMode()
        robot.slam.options = MOESlamOptions(0.0, 0.0, 0.0)
        Log.e("why", "yes")
        gpad1.a.onKeyDown {
            Log.e("restarting", "yes")
            robot.slam.restart()
        }
    }

    override fun mainLoop() {
        super.mainLoop()
        //        if (laped.milliseconds().toInt() > diff) {
        //            val robotPose = robot.slam.getRawPose()
        //            MOESocketHandler.moeWebServer.broadcast("data/slam/${robotPose[0]},${robotPose[1]},${robot.gyro.getRawAngle()}")
        //            laped.reset()
        //        }
        telemetry.addData("theta", robot.slam.getTheta().toFixed(3))
        val raw = robot.slam.getRawPose().contentToString()
        telemetry.addData("rawPose", raw)
        printWriter.write(raw)
        telemetry.addData("rawPoseInCM", robot.slam.getRawPose().map { it * 100 })
        telemetry.addData("rawPoseOffset", robot.slam.getRawOffsetPose())
        telemetry.addData("cameraPosition", robot.slam.getCameraPose() * MOEConstants.Units.ASTARS_PER_METER)
        telemetry.addData("robotaxis", robot.slam.getRobotPoseInCameraAxis() * MOEConstants.Units.ASTARS_PER_METER)
        telemetry.addData("scaledPosition", robot.slam.getScaledRobotPose())
        //        telemetry.addData("position", robot.slam.getRobotPose())
        telemetry.update()
    }
}