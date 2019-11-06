package org.firstinspires.ftc.teamcode.test

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.external.hello
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "KotlinTest")
class KotlinTest : MOETeleOp() {

    override fun initOpMode() {
        Log.e("stuffed",hello())
        telemetry.addData("testagain")
    }

    override fun loopStuff() {
//        mainGamepad.onButton(Button.A) {
//            Log.e("stuff", "A is $it")
//        }
    }

}