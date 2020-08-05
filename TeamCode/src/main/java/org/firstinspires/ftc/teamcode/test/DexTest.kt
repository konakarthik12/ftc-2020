package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.robotcore.internal.system.AppUtil
import org.firstinspires.ftc.robotcore.internal.ui.UILocation

@Autonomous
class DexTest : OpMode() {
    fun test() {
        Log.e("plz", "work")
    }

    private val code = 135

    override fun init() {
//        AppUtil.getInstance().showProgress(UILocation.BOTH, "progress", 0.5)
//        Client.lastSocket.getOutputStream().write(code)

    }

    override fun init_loop() {
        telemetry.addData("hello", "word $code")
    }

    var count = 0L
    var progress = 0L
    override fun loop() {
        count++
        val speed = 300
        if (count / speed > progress) progress = count / speed
        AppUtil.getInstance().showProgress(UILocation.BOTH, "progress", progress / 100.0)
        telemetry.addData("count", progress.toString())
    }
}