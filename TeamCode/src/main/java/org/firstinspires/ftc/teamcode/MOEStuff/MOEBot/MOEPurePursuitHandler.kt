package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.constants.MOEConstants.PurePursuit
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.PurePursuit.MOEPurePursuitSystem
import org.firstinspires.ftc.teamcode.utilities.external.PurePursuit.PurePursuitPoint
import kotlin.math.abs

class MOEPurePursuitHandler {
    private fun isFinished(pose: Point, destX: Double, destY: Double): Boolean {
        val xDifference = abs(pose.x - destX)
        val yDifference = abs(pose.y - destY)
        return xDifference < PurePursuit.FINISHED_TOLERANCE || yDifference < PurePursuit.FINISHED_TOLERANCE
    }

//    fun move(x: Double, y: Double, t: Double) {
//        var pose = robot.slam.transformation.pose
//
//        // TODO: Add Pathfinding algorithm.
//        val purePursuit = MOEPurePursuitSystem(pose.x, pose.y, x, y, PurePursuit.DefaultOptions)
//
//        while (!isFinished(pose, x, y)) {
//            pose = robot.slam.transformation.pose
//
//            val (leftActualVelocity, rightActualVelocity) = robot.chassis.getFrontVelocities()
//
//            val (leftTargetVelocity, rightTargetVelocity) = purePursuit.getWheelVelocities(
//                    currentPosition = PurePursuitPoint(pose),
//                    currentHeading = robot.gyro.angle,
//                    leftActualVelocity = leftActualVelocity,
//                    rightActualVelocity = rightActualVelocity
//            )
//
//            robot.chassis.setVelocity(leftTargetVelocity, rightTargetVelocity)
//        }
//
//        // TODO: Make a turn to degrees call at the end of the function.
//    }
}
