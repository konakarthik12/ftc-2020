package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOETurnPid
import org.firstinspires.ftc.teamcode.utilities.internal.get

@Autonomous
class TurnPidTest : MOERegularTest() {
    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["chicken"]
    }

    var pid = MOETurnPid()

    override fun initOpMode() {
        pid.setOptions(org.firstinspires.ftc.teamcode.constants.MOEPidConstants.PositionalPid.DefaultOptions.turnOptions)

        pid.input = {
            robot.gyro.angle
        }
        pid.output = {
            robot.chassis.turnPower(it)
        }
        pid.setOutputLimits(0.5)
        pid.setpoint = { -90.0 }

        //        ref.setValue(MOEPidValues(0.01, 0.0, 0.0))
        //        telemetry.addData("testagain")
        //        robot.odometry.servos.initServosUp()
    }

    val timer = ElapsedTime()
    override fun run() {
        timer.reset()
//        robot.chassis.encoders.moveBackwardInches(23.5)
        pid.run()
        val tookTime = timer.seconds()
        while (opModeIsActive()) {
            telemetry.addData("took time", tookTime)
            telemetry.addData("angle", robot.gyro.angle)
            telemetry.update()
        }

    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply {
            useSlam = true
            useVuforia = true
        }
    }
//    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
////        val options = dataSnapshot.getValue(MOEPidOptions::class.java)!!
////        Log.e("stuff", options.toString())
//
//    }
    //    private fun waitForGamepad() {
    //        while (g)
    //    }
}