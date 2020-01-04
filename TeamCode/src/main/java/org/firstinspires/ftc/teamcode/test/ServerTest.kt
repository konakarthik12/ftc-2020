package org.firstinspires.ftc.teamcode.test

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOESocketHandler.moeWebServer
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import kotlin.random.Random

@TeleOp(name = "ServerTest")
class ServerTest : MOETeleOp() {


    override fun initOpMode() {
        Log.e("stuffe", moeWebServer.address.toString())
        telemetry.addData(moeWebServer.address)
    }

    override fun mainLoop() {
        val randomValues = List(2) { Random.nextInt(-50, 50) }

        moeWebServer.broadcast("data/slam/${randomValues.joinToString(separator = ",")}")
        Thread.sleep(500)
    }

}