package org.firstinspires.ftc.teamcode.test

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOESocketHandler.moeWebServer
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOELoopedOpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import kotlin.random.Random

@TeleOp(name = "ServerTest")
class ServerTest : MOELoopedOpMode() {


    override fun initOpMode() {
//        Log.e("stuffe", moeWebServer.address.toString())
//        telemetry.addData("trying", moeWebServer.address)
    }

    val timer = ElapsedTime()
    override fun afterInit() {
        super.afterInit()
        timer.reset()
    }

    val wait = 200
    override fun loop() {
        while (timer.milliseconds() < wait) {
            return
        }
        timer.reset()
//        moeWebServer.broadcast(getData())
//        moeWebServer.broadcast("odo/moetion/${randomValues.joinToString(separator = ",")}")
    }

}