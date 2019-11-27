package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "PurePursuitTest")
class PurePursuitTest : MOETeleOp() {
    override fun initOpMode() {
        val string = Point(2.0, 4.0)
        Log.e("string", string.toString())
        telemetry.addData("testagain$string")
    }

    override fun loopStuff() {
        robot.purePursuit.move(0.0, 50.0, 0.0)
    }
}
