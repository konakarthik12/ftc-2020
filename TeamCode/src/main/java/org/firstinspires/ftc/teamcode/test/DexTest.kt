package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class DexTest : OpMode() {
    fun test() {
        Log.e("plz", "work")
    }

    private val code = 54
    override fun init() {

    }

    override fun init_loop() {
        telemetry.addData("hello", "word $code")
    }

    override fun loop() {
    }
}