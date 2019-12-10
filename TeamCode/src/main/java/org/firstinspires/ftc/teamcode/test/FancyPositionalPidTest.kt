package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.*
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.get
import org.firstinspires.ftc.teamcode.utilities.toFixed

@TeleOp(name = "FancyPositionalPidTest")
class FancyPositionalPidTest : MOETeleOp() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref
    }

    override fun initOpMode() {

    }

    lateinit var pid: MOEPositionalSystemPid

    override fun mainLoop() {
        pid.setSetpoints(96.0, 96.0, 90.0)
        pid.setOutputLimits(1.0, 1.0, 1.0)

        while (moeOpMode.iOpModeIsActive()) {
            val pose = robot.slam.getScaledRobotPose()
            val powers = pid.getPowers(pose.x, pose.y, robot.gyro.angle)

            telemetry.addData(robot.gyro.getRawAngle().toFixed(4))
            telemetry.addData(powers.FLP.toFixed(4))
            telemetry.addData(powers.FRP.toFixed(4))
            telemetry.addData(powers.BLP.toFixed(4))
            telemetry.addData(powers.BRP.toFixed(4))
            telemetry.addData(pid)
            telemetry.update()

            robot.chassis.setPower(powers)
        }
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val xOptions = dataSnapshot.child("positionX").getValue(MOEPidValues::class.java)!!
        val yOptions = dataSnapshot.child("positionY").getValue(MOEPidValues::class.java)!!
        val tOptions = dataSnapshot.child("positionT").getValue(MOEPidValues::class.java)!!

        pid = MOEPositionalSystemPid(xOptions, yOptions, tOptions)
    }
}
