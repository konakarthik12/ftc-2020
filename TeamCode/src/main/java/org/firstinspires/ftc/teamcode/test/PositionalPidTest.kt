package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers.Companion.mechanumToPowers
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.*
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlamOptions
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.get

@TeleOp(name = "PositionalPidTest")
class PositionalPidTest : MOETeleOp(useSlam = true) {
    //    lateinit var xPid: MOEPositionalPid
    //    lateinit var yPid: MOEPositionalPid
    //    lateinit var tPid: MOETurnPid
    lateinit var systemPid: MOEPositionalSystemPid

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["positionalPID"]
    }

    override fun initOpMode() {
        telemetry.addData("testagain")

        robot.slam.options = MOESlamOptions(0.0, 0.0, 0.0)
        //        SlamHandler.t265Handler.restart()
        //        robot.slam.waitForRestart()
        //        systemPid = MOEPositionalSystemPid(
        //                1.0, 0.0, 0.0, 0.0,
        //                1.0, 0.0, 0.0, 0.0,
        //                1.0, 0.0, 0.0, 0.0
        ////        )
        //        systemPid.setSetpoints(0.0)
        //
        //        val limit = 0.5
        //        systemPid.setOutputLimits(limit);
    }

    override fun mainLoop() {
        val pose = robot.slam.getScaledRobotPose()

        val str = systemPid.xPid.getOutput(pose.x)
        val fwd = systemPid.yPid.getOutput(pose.y)
        val rot = systemPid.tPid.getOutput(robot.gyro.angle)

        telemetry.addData("STR", str)
        telemetry.addData("FWD", fwd)

        telemetry.addData("curPose", pose.x)
        telemetry.addData("curAngle", robot.gyro.angle)


        val powers = mechanumToPowers(0.0, str, gpad1.right_stick_x)
        telemetry.addData("velocityad", powers.toString())
        telemetry.update()
        powers *= 15.0
        if (gamepad1.a) {
            robot.chassis.setVelocity(0.0)
            return
        }
        robot.chassis.setVelocity(powers)
        //        while (gamepad1.a) {
        //            robot.chassis.setVelocity(0.0)
        //            telemetry.addData("waiting for key")
        //            telemetry.update()
        //        }
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val xOptions = dataSnapshot["x"].getValue(MOEPidValues::class.java)!!
        val yOptions = dataSnapshot["y"].getValue(MOEPidValues::class.java)!!
        val tOptions = dataSnapshot["t"].getValue(MOEPidValues::class.java)!!

        systemPid = MOEPositionalSystemPid(xOptions, yOptions, tOptions)
        systemPid.setOutputLimits(0.5)
        systemPid.setSetpoints(100.0, 0.0, 0.0)
        Log.e("Positnal pid", xOptions.toString())
    }
}
