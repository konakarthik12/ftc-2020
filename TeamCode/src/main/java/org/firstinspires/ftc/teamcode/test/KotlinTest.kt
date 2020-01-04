package org.firstinspires.ftc.teamcode.test

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@TeleOp(name = "KotlinTest")
class KotlinTest : MOETeleOp() {
    override fun initOpMode() {
        telemetry.addData("testagain")
    }

    override fun mainLoop() {
//        mainGamepad.onButton(Button.A) {
//            Log.e("stuff", "A is $it")
//        }
    }

}