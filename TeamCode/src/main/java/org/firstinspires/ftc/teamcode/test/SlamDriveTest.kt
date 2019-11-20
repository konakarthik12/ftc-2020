package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOESocketHandler.moeWebServer
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.delete
import org.firstinspires.ftc.teamcode.utilities.get
import java.lang.RuntimeException

@TeleOp(name = "SlamDriveTest")
class SlamDriveTest : MOETeleOp(useSlam = true) {
    val laped = ElapsedTime()
    var diff: Int = 1000
    override fun initOpMode() {
        ref.delete()
        telemetry.addData("testagain")
        robot.odometry.servos.initServosUp()
        robot.slam
    }

    override fun loopStuff() {
        if (laped.milliseconds().toInt() > diff) {
            val robotPose = robot.slam.getRawPose()
            moeWebServer.broadcast("data/slam/${robotPose[0]},${robotPose[1]},${robot.gyro.getRawAngle()}")

            laped.reset();
        }
        telemetry.addData("slam", robot.gyro.getRawAngle())
        telemetry.addData("rawPose", robot.slam.getRawPose().toString())
        telemetry.addData("cameraPosition", robot.slam.getCameraPose())
        telemetry.addData("position", robot.slam.getRobotPose())
        telemetry.update()

        val maxPower = 1.0
        val scaleX = 1
        val scaleY = 0.6
        val scaleRot = 0.3

        var rawX = gamepad1.left_stick_x.toDouble()
        var rawY = (-gamepad1.left_stick_y).toDouble()
        var rot = gamepad1.right_stick_x.toDouble()
        rawX *= scaleX
        rawY *= scaleY
        rot *= scaleRot

        var fwd = rawY
        fwd *= fwd * fwd
        var FRP = fwd - rawX - rot
        var FLP = fwd + rawX + rot
        var BRP = fwd + rawX - rot
        var BLP = fwd - rawX + rot

        var max = if (FRP > maxPower) FRP else maxPower
        if (max < FRP) {
            max = FRP
        }
        if (max < BLP) {
            max = BLP
        }
        if (max < BRP) {
            max = BRP
        }
        if (max < FLP) {
            max = FLP
        }

        FLP /= max
        FRP /= max
        BLP /= max
        BRP /= max

        robot.chassis.setPower(FLP, FRP, BLP, BRP)
    }

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["slamyboy"]
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        try {
            diff = dataSnapshot.getValue(Int::class.java) ?: return
            Log.e("slow", diff.toString())
        } catch (e: RuntimeException) {

        }
    }
}

//private fun DatabaseReference.pushData(function: () -> Point3) {
//
//}




