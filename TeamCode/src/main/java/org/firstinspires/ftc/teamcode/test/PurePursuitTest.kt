package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOELoopedOpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.PurePursuit.*
import org.firstinspires.ftc.teamcode.utilities.external.toPrecision
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import org.firstinspires.ftc.teamcode.utilities.internal.get

@TeleOp(name = "AAPurePursuitTest")
class PurePursuitTest : MOELoopedTest() {
    val points = listOf(
            PurePursuitPoint(0.0, 0.0),
            PurePursuitPoint(0.0, 100.0)
    )
    //    var options = MOEConstants.PurePursuit.DefaultOptions
    lateinit var path: MOEPurePursuitPath
    lateinit var options: MOEPurePursuitOptions
    override fun initOpMode() {
        telemetry.addData("wait for", "slam")
        telemetry.update()
        telemetry.update()
        robot.slam.restart()
        //        val string = Point(2.0, 4.0)
        //        Log.e("string", string.toString())
        //        telemetry.addData("testagain$string")
        //        val purePursuit = MOEPurePursuitSystem(pose.x, pose.y, pose.x, pose.y + 100, MOEConstants.PurePursuit.DefaultOptions)

    }


    var lastKnownPointIndex = 0
    fun getWheelVelocities(currentPosition: PurePursuitPoint,
                           currentHeading: Double,
                           leftActualVelocity: Double,
                           rightActualVelocity: Double): Pair<Double, Double> {

        lastKnownPointIndex = path.getClosestPointIndex(lastKnownPointIndex, currentPosition)
//        telemetry.addData("lastKnownPointIndex", lastKnownPointIndex)
        val closestPoint: PurePursuitPoint = path[lastKnownPointIndex]
        telemetry.addData("closestPoint", closestPoint)

        val heading = currentHeading

        val lookaheadPoint = path.getLookaheadPointFromPathing(lastKnownPointIndex, currentPosition)
        telemetry.addData("lookAheadPoint", lookaheadPoint)

        val curvature = getSignedCurvatureFromLookaheadPoint(
                lookahead = lookaheadPoint,
                currPos = currentPosition,
                heading = heading,
                lookaheadDistance = options.lookAheadDistance
        )

        telemetry.addData("gyro", robot.gyro.angle.toPrecision())
        telemetry.addData("curvature", curvature)
        if (gpad1.left.trigger.button.isPressed) {
            Log.e("hold", "up")
        }
        val leftTV = getLeftWheelTargetVelocity(closestPoint.velocity, curvature)
        val rightTV = getRightWheelTargetVelocity(closestPoint.velocity, curvature)
        return leftTV to rightTV
    }

    private fun getLeftWheelTargetVelocity(targetVelocity: Double, curvature: Double): Double {
        return targetVelocity * (2 + curvature * options.track_width) / 2
    }

    private fun getRightWheelTargetVelocity(targetVelocity: Double, curvature: Double): Double {
        return targetVelocity * (2 - curvature * options.track_width) / 2
    }

    //    override fun run() {
    //        while (opModeIsActive()) {
    //            mainLoop()
    //            telemetry.update()
    //        }
    //    }


    override fun mainLoop() {

        val pose = robot.slam.getCameraPose()
        pose *= MOEConstants.Units.ASTARS_PER_METER
        telemetry.addData("pose", pose.toString())
        val (leftActualVelocity, rightActualVelocity) = robot.chassis.getFrontVelocities()

        val (leftTargetVelocity, rightTargetVelocity) = getWheelVelocities(
                currentPosition = PurePursuitPoint(pose),
                currentHeading = robot.gyro.angle,
                leftActualVelocity = leftActualVelocity,
                rightActualVelocity = rightActualVelocity
        )
        telemetry.addData("leftTarget", leftTargetVelocity)
        telemetry.addData("rightTarget", rightTargetVelocity)
        telemetry.addData("rightTrig", gpad1.right.trigger.button.isPressed)
        telemetry.update()

        if (gpad1.right.trigger.button.isPressed) {
            robot.chassis.setVelocity(0.0)
            return
        }

        robot.chassis.setVelocity(leftTargetVelocity, rightTargetVelocity)
    }

    override fun getRobotConfig(): MOEBotConfig {
        return super.getRobotConfig().apply { useSlam = true }
    }

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["purest-pursuit"]
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        options = dataSnapshot.getValue(MOEPurePursuitOptions::class.java)!!
        path = MOEPurePursuitPath(points, options)

    }
    //        robot.purePursuit.move(0.0, 50.0, 0.0)
}

