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
    lateinit var xPid: MOEPositionalPid
    lateinit var yPid: MOEPositionalPid
    lateinit var tPid: MOETurnPid

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["positionalPID"]
    }

    override fun initOpMode() {
        telemetry.addData("testagain")
        robot.slam.options = MOESlamOptions(0.0, 0.0, 0.0)
        SlamHandler.t265Handler.restart()

        xPid.setpoint = 192.0
        yPid.setpoint = 192.0
        tPid.setpoint = 0.0
        xPid.setOutputLimits(0.5)
        yPid.setOutputLimits(0.5)
        tPid.setOutputLimits(0.5)
    }

    override fun mainLoop() {
        val pose = robot.slam.getScaledRobotPose()
        val str = xPid.getOutput(pose.x)
        val fwd = yPid.getOutput(pose.y)
        val rot = tPid.getOutput(robot.gyro.angle)
        telemetry.addData("STR", str)
        telemetry.addData("FWD", fwd)
        telemetry.addData("ROT", rot)

        robot.chassis.setPower(mechanumToPowers(fwd = yPid.getOutput(pose.y),
                                                str = xPid.getOutput(pose.x),
                                                rot = tPid.getOutput(robot.gyro.angle)))
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val xOptions = dataSnapshot["x"].getValue(MOEPidValues::class.java)!!
        xPid = MOEPositionalPid(xOptions)

        val yOptions = dataSnapshot["y"].getValue(MOEPidValues::class.java)!!
        yPid = MOEPositionalPid(yOptions)

        val tOptions = dataSnapshot["t"].getValue(MOEPidValues::class.java)!!
        tPid = MOETurnPid(tOptions)
    }
}
