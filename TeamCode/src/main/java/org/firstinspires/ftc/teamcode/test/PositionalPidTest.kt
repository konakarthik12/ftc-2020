package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.*
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlamOptions
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.get
import org.firstinspires.ftc.teamcode.utilities.toFixed

@TeleOp(name = "PositionalPidTest")
class PositionalPidTest : MOETeleOp(useSlam = true) {
    lateinit var pid: MOEPositionalPid

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["positionY"]
    }

    override fun initOpMode() {
        telemetry.addData("testagain")
        robot.slam.options = MOESlamOptions(0.0, 0.0, 0.0)
        SlamHandler.t265Handler.restart()

        pid = MOEPositionalPid(MOEPidValues(P = 0.001, I = 0.0, D = 0.0, F = 0.0))
        pid.setpoint = 192.0
        pid.setOutputLimits(0.5)
    }

    override fun mainLoop() {
        val y = robot.slam.getScaledRobotPose().y
        val output = pid.getOutput(y)

        telemetry.addData(y.toFixed(4))
        telemetry.addData(output.toFixed(4))
        telemetry.addData(pid)
        telemetry.update()

        robot.chassis.setPower(output)
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
//        val options = dataSnapshot.getValue(MOEPidValues::class.java)!!
//        pid = MOEPositionalPid(options)
    }
}
