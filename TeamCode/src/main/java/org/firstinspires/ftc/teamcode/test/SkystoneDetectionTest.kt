package org.firstinspires.ftc.teamcode.test


import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle
import org.firstinspires.ftc.teamcode.utilities.internal.addData

@Disabled
@Autonomous(name = "SkystoneDetection", group = "test")
class SkystoneDetectionTest : MOEAuton() {


    //    lateinit var data: DataSnapshot
    var cropRectangle: Rectangle? = null

    override fun initOpMode() {

        telemetry.apply {
            addData("data")
        }
    }


    override fun run() {
        telemetry.addData("running")
        while (opModeIsActive()) {
            telemetry.update()
        }
    }


}


