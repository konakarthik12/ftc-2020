package org.firstinspires.ftc.teamcode.autonomous

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import org.firstinspires.ftc.teamcode.utilities.internal.getSkyStoneLocationFromBitmap

@Autonomous(name = "AutonTemplate")
class AutonTemplate : MOEAuton() {
    override fun initOpMode() {
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")
    }

    override fun run() {
        val bm = robot.camera.getCroppedBitmap(getAutonConfig().positionConfig.skystoneCropRect)!!
        val location = getSkyStoneLocationFromBitmap(bm)

//        when (location) {
//            SkyStoneLocation.LEFT -> {
//                robot.chassis.moveTo(getAutonConfig().positionConfig.leftSkystonePosition)
//            }
//            SkyStoneLocation.MIDDLE -> {
//                robot.chassis.moveTo(getAutonConfig().positionConfig.middleSkystonePosition)
//            }
//            SkyStoneLocation.RIGHT -> {
//                robot.chassis.moveTo(getAutonConfig().positionConfig.rightSkystonePosition)
//            }
//        }

        robot.harvester.setPower(0.6)
        robot.chassis.encoders.moveForwardAStars(30.0, 0.5)
        robot.chassis.encoders.moveBackwardAStars(35.0, 0.5) // Adding 5 astars for good measure.

        robot.chassis.moveTo(-1.0 /* foundation */, -1.0)
    }

    //    override fun getInitialSlam(): Point {
    //        return super.getInitialSlam()
    //    }
}
