package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp

@TeleOp
class CounterTest : CompTeleOp() {
    override fun initOpMode() {
        super.initOpMode()
        gpad1.a.onKeyDown {
            Log.e("what", "up")
            counter++
        }
    }

    var counter = 0

    override fun mainLoop() {
        super.mainLoop()
        super.log()
        telemetry.addData("Counter", counter)
    }
}