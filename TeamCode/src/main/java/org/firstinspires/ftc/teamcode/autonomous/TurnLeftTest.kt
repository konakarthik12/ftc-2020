package org.firstinspires.ftc.teamcode.autonomous

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData

@Autonomous(name = "TurnLeft")
class TurnLeftTest : MOEAuton() {


    override fun initOpMode() {
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")
    }

    override fun run() {
        val power = 0.6
        robot.chassis.setPower(0.5 * power, 0.0 * power, -1.0 * power, 1.0 * power)
        Thread.sleep(2000)
        robot.chassis.stop();
    }

}