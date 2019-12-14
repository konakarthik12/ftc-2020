package org.firstinspires.ftc.teamcode.autonomous

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.utilities.SkyStoneLocation
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.getSkyStoneLocationFromBitmap
import org.firstinspires.ftc.teamcode.utilities.wait

@Autonomous(name = "FoundationAuton")
class FoundationAuton : MOEAuton(isLeft = true) {
    override fun initOpMode() {
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")
    }

    override fun run() {
        robot.chassis.astar.moveForwardAStars(72.0, 0.3)

        robot.foundation.closeServo()
        wait(3000)

        robot.chassis.astar.moveBackwardSlamAStars(69.0, 0.3)
        wait(3000)

        robot.foundation.openServo()
        wait(3000)

        robot.chassis.astar.jitter(4.0, 0.4)
    }
}
