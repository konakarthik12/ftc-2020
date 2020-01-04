package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPidValues
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOETurnPid
import org.firstinspires.ftc.teamcode.utilities.internal.get

@TeleOp
class TurnPidTest : MOERegularTest() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["chicken"]
    }

    var pid = MOETurnPid()

    override fun initOpMode() {
        pid.input = {
            robot.gyro.angle
        }
        pid.output = {
            robot.chassis.turnPower(it)
        }
        pid.endCondition = { false }
        robot.slam.restart()
        robot.slam.waitForData()

        //        ref.setValue(MOEPidValues(0.01, 0.0, 0.0))
        //        telemetry.addData("testagain")
        //        robot.odometry.servos.initServosUp()
    }


    override fun run() {
        pid.run()

    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val options = dataSnapshot.getValue(MOEPidValues::class.java)!!
        Log.e("stuff", options.toString())
        pid.setOptions(options)

    }
    //    private fun waitForGamepad() {
    //        while (g)
    //    }
}