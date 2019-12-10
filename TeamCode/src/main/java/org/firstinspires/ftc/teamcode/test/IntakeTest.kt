package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "IntakeTest")
class IntakeTest : MOETeleOp() {
    override fun initOpMode() {
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")
    }

    override fun mainLoop() {
        val P = (gamepad1.left_trigger - gamepad1.right_trigger).toDouble() * 0.4
        telemetry.addData("Power: ", P)
        telemetry.update()
        robot.harvester.setPower(P)

    }
}
