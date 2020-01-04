package org.firstinspires.ftc.teamcode.autonomous

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.internal.SkyStoneLocation
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import org.firstinspires.ftc.teamcode.utilities.internal.getSkyStoneLocationFromBitmap

@Autonomous(name = "AutonTemplate")
class AutonTemplate : MOEAuton(isLeft = true) {
    override fun initOpMode() {
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")
    }

    override fun run() {
        val bm = robot.camera.getCroppedBitmap(config.skystoneCropRect)!!
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

    //    override fun getInitialSlam(): Point {
    //        return super.getInitialSlam()
    //    }
}
