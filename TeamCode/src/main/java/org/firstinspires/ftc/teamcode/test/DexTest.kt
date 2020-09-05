package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.robotcore.internal.system.AppUtil
import org.firstinspires.ftc.robotcore.internal.ui.UILocation
import java.lang.Thread.sleep

@Autonomous
class DexTest : OpMode() {
    fun test() {
        Log.e("plz", "work")
    }

    private val code = 202

    override fun init() {
        Log.e("testing","code")
    }

    override fun init_loop() {
        Log.e("testing","code")
        sleep(500)
//        telemetry.addData("hello", "word $code")
    }

    var sent = false
    override fun loop() {
        if (sent) return
        AppUtil.getInstance().dismissProgress(UILocation.BOTH)
        sent = true
//        AppUtil.getInstance().showToast(UILocation.BOTH, "code: $code")
        AppUtil.getInstance().showProgress(UILocation.BOTH, "progress", code / 300.0)
//        telemetry.addData("count", progress.toString())
    }
}