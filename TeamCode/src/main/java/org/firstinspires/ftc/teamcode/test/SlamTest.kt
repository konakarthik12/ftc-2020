package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOEServer
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOESocketHandler
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.get

@TeleOp(name = "SlamTest")
class SlamTest : MOETeleOp(useSlam = true) {
    //    val laped = ElapsedTime()
    var speed: Int = 1000;

    override fun loopStuff() {
        //        count++
        //        telemetry.addData("loop", count++)
        val quadTheta = robot.slam.getRawTheta()
        MOESocketHandler.moeWebServer.broadcast("data/slam/0,0,0,$quadTheta")
        Thread.sleep(speed.toLong())
//        telemetry.addData("", robot.slam.getRawTheta())
        telemetry.addData("slam", robot.slam.getRawTheta())
        telemetry.update()
    }

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["slamyboy"]
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        speed = dataSnapshot.getValue(Int::class.java) ?: return
    }

    override fun initOpMode() {
        telemetry.addData("testagain")
    }
}
