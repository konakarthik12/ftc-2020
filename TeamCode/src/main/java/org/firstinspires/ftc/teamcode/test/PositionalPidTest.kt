package org.firstinspires.ftc.teamcode.test

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
    lateinit var systemPidOld: MOEPositionalSystemPid

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["positionalPID"]
    }

    override fun initOpMode() {
        telemetry.addData("testagain")

        robot.slam.options = MOESlamOptions(0.0, 0.0, 0.0)
        SlamHandler.t265Handler.restart()
        systemPidOld = MOEPositionalSystemPid(
                1.0, 0.0, 0.0, 0.0,
                1.0, 0.0, 0.0, 0.0,
                1.0, 0.0, 0.0, 0.0)
        systemPidOld.setSetpoints(0.0)
        //        xPid.setpoint = 192.0
        //        yPid.setpoint = 192.0
        //        tPid.setpoint = 0.0
        val limit = 0.5
        systemPidOld.setOutputLimits(limit);
        //        xPid.setOutputLimits(limit)
        //        yPid.setOutputLimits(limit)
        //        tPid.setOutputLimits(limit)
    }

    override fun mainLoop() {
        val pose = robot.slam.getScaledRobotPose()

        val str = systemPidOld.xPid.getOutput(pose.x)
        val fwd = systemPidOld.yPid.getOutput(pose.y)
        val rot = systemPidOld.tPid.getOutput(robot.gyro.angle)
        telemetry.addData("STR", str)
        telemetry.addData("FWD", fwd)
        telemetry.addData("ROT", rot)
        telemetry.addData("curAngle", robot.gyro.angle)
        //        telemetry.addData("curAngle", robot.gyro.angle)

        val powers = mechanumToPowers(0.0,0.0,
//                systemPid.yPid.getOutput(pose.y),
//                systemPid.xPid.getOutput(pose.x),
                systemPidOld.tPid.getOutput(robot.gyro.angle)
        )
        powers *= 50.0;
        robot.chassis.setVelocity(powers)
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val xOptions = dataSnapshot["x"].getValue(MOEPidValues::class.java)!!

        val yOptions = dataSnapshot["y"].getValue(MOEPidValues::class.java)!!

        val tOptions = dataSnapshot["t"].getValue(MOEPidValues::class.java)!!
        systemPidOld = MOEPositionalSystemPid(xOptions, yOptions, tOptions)
    }
}
