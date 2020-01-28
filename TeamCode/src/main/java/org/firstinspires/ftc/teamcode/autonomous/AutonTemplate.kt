package org.firstinspires.ftc.teamcode.autonomous

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.autonomous.constants.AutonConstants
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation
import org.firstinspires.ftc.teamcode.autonomous.vision.getSkyStoneLocationFromBitmap

@Autonomous(name = "AutonTemplate")
class AutonTemplate : MOEAuton() {
    override fun initOpMode() {

    }

    override fun run() {
        val location = getSkyStoneLocation()

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

        robot.intake.setPower(0.6)
        robot.chassis.encoders.moveForwardAStars(30.0, 0.5)
        robot.chassis.encoders.moveBackwardAStars(35.0, 0.5) // Adding 5 astars for good measure.

        robot.chassis.moveTo(-1.0 /* foundation */, -1.0)
    }

    private fun getSkyStoneLocation(): SkyStoneLocation {
        val bm = robot.vuforia.getCroppedBitmap(AutonConstants.Skystone.SKYSTONE_CROP)!!
        return getSkyStoneLocationFromBitmap(bm)
    }

    //    override fun getInitialSlam(): Point {
    //        return super.getInitialSlam()
    //    }
}
