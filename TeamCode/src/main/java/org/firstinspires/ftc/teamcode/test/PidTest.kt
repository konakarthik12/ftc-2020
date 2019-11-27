package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEFancyPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPidValues
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOETurnPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.get
import org.firstinspires.ftc.teamcode.utilities.toFixed

@TeleOp(name = "PidTest")
class PidTest : MOETeleOp() {


    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["chicken"]
    }

    override fun initOpMode() {
//        ref.setValue(MOEPidValues(0.01, 0.0, 0.0))
        //        telemetry.addData("testagain")
        //        robot.odometry.servos.initServosUp()
    }

    lateinit var pid:MOETurnPid

    override fun loopStuff() {
            pid.setpoint = 180.0
        //        pid.setOutputLimits(0.25)
        while (moeOpMode.opModeIsActive()) {

            val output = pid.getOutput(robot.gyro.getRawAngle())
            telemetry.addData(robot.gyro.getRawAngle().toFixed(4))
            telemetry.addData(output.toFixed(4))
            telemetry.addData(pid)
            telemetry.update()
            robot.chassis.turnPower(output)
            //            waitForGamepad()
        }
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val options = dataSnapshot.getValue(MOEPidValues::class.java)!!
        Log.e("stuff",options.toString())
        pid = MOETurnPid(options)
    }
    //    private fun waitForGamepad() {
    //        while (g)
    //    }
}