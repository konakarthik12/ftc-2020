package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.get

@TeleOp(name = "SlamTest")
class SlamTest : MOETeleOp(useSlam = true) {
    val laped = ElapsedTime()
    override fun loopStuff() {
        //        count++
        //        telemetry.addData("loop", count++)
        telemetry.addData("slam", robot.slam.getRawTheta())
        telemetry.update()

    }

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["slamyboy"]
    }

    override fun initOpMode() {

        telemetry.addData("testagain")
    }
}