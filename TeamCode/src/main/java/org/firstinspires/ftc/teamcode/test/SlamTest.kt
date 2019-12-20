package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamT265Handler
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.get

@TeleOp(name = "SlamTest")
class SlamTest : MOETeleOp(useSlam = true) {
    //    val laped = ElapsedTime()
    var speed: Int = 1000;

    override fun mainLoop() {
        SlamHandler.t265Handler.restart()
        val pose = robot.slam.getRawPose()

        telemetry.addData("slam", pose)
        telemetry.addData("slam", robot.slam.getRobotPose())
        telemetry.update()
    }

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["slamyboy"]
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        //        speed = dataSnapshot.getValue(Int::class.java) ?: return
        Log.e("changed", "changed")
    }

    override fun initOpMode() {
        gpad1.a.onKeyDown {
            robot.slam.restart()
        }
        telemetry.addData("testagain")
    }
}
