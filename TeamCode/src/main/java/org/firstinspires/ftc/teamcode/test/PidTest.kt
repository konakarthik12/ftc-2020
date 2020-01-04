package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPidValues
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOETurnPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import org.firstinspires.ftc.teamcode.utilities.internal.get
import org.firstinspires.ftc.teamcode.utilities.external.toPrecision

@TeleOp
class PidTest : MOETeleOp() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["turn"]
    }

    var pid = MOETurnPid()

    override fun initOpMode() {
        gamepad1.setJoystickDeadzone(0.0F)
        gpad1.x.onKeyDown {
            goal+=180
        }
        pid.input = {
            robot.gyro.angle
        }
        pid.output = {
            robot.chassis.turnPower(it)
        }
        pid.setOutputLimits(0.4)
        pid.endCondition = { false }
        //        robot.slam.restart()
        //        robot.slam.waitForRestart()

        //        ref.setValue(MOEPidValues(0.01, 0.0, 0.0))
        //        telemetry.addData("testagain")
        //        robot.odometry.servos.initServosUp()
    }

    //    val multi = 40.0
    var goal = 0.0

    override fun mainLoop() {
        goal += gpad1.left.stick.x()

        var output = pid.getOutput(pid.input(), goal)

        telemetry.addData(robot.gyro.getRawAngle().toPrecision(4))
        telemetry.addData((output).toPrecision(4))
        telemetry.addData("goal", goal)
        telemetry.update()
        if (gpad1.y.isToggled) output = 0.0
        robot.chassis.turnPower(output)
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val options = dataSnapshot.getValue(MOEPidValues::class.java)!!
        Log.e("stuff", options.toString())
        pid.setOptions(options)
        pid.reset()

    }
    //    private fun waitForGamepad() {
    //        while (g)
    //    }
}