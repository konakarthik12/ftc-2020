package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "SlamTest")
class SlamTest : MOETeleOp() {
    override fun loopStuff() {
        telemetry.addData("slam", robot.slam.toString())
        telemetry.update()
    }

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    override fun initOpMode() {

        telemetry.addData("testagain")
    }
}