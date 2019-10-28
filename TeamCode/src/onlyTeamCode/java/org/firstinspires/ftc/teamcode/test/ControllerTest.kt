package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "ControllerTest")
class ControllerTest : MOETeleOp() {
    override fun run() {
        while (opModeIsActive()) {
            loopStuff()
        }
    }


    override fun initOpMode() {
        telemetry.addData("testagain")
    }

    private fun loopStuff() {
        mainGamepad.onButton(Button.A) {
            Log.e("stuff", "A is $it")
        }
    }

}