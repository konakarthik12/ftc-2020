package org.firstinspires.ftc.teamcode.autonomous

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.utilities.SkyStoneLocation
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.getSkyStoneLocationFromBitmap

@Autonomous(name = "AutonTemplete")
class AutonTemplate : MOEAuton() {
    override fun initOpMode() {
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")
    }

    override fun run() {
        val bm = robot.camera.getCroppedBitmap(MOEConstants.Autonomous.Template.SKYSTONE_CROP)!!
        val location = getSkyStoneLocationFromBitmap(bm)

        when (location) {
            SkyStoneLocation.LEFT -> {

            }
            SkyStoneLocation.MIDDLE -> {

            }
            SkyStoneLocation.RIGHT -> {

            }
        }

        robot.chassis.moveTo(-1.0 /* foundation */, -1.0)
    }
}
