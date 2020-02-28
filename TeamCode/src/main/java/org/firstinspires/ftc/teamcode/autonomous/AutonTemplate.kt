package org.firstinspires.ftc.teamcode.autonomous

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.autonomous.sideconfig.AutonSideConfig
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation.*
import org.firstinspires.ftc.teamcode.autonomous.vision.getSkyStoneLocationFromBitmap
import org.firstinspires.ftc.teamcode.utilities.internal.wait

abstract class AutonTemplate(val side: AutonSideConfig) : MOEAuton() {
    override fun initOpMode() {
        robot.autonArms.right.raiseArm()
        robot.autonArms.right.openClaw()
        robot.foundation.moveUp()
    }

    override fun run() {
        val location = getSkyStoneLocation(side.cropRectangle)
        val distances = side.getDistances(location)
        telemetry.addData("locatiob", location)
        telemetry.addData("gyro", robot.gyro.angle)
        telemetry.update()

        robot.chassis.encoders.moveBackwardInches(distances[0])

        robot.chassis.turnTo(90.0)
//        val verticalDistance = when (location) {
//            LEFT -> 1.5
//            MIDDLE -> -1.75
//            RIGHT -> -8.0
//        }
//        telemetry.addData("distance", verticalDistance)
//        telemetry.update()
        robot.chassis.encoders.moveVertical(distances[1])
//        val strafeDistance = when (location) {
//            LEFT -> 4.5
//            MIDDLE -> 1.0
//            RIGHT -> 1.5
//        }
        robot.chassis.turnTo(90.0)
        robot.chassis.encoders.moveRightInches(distances[2])
//        wait(50)
        robot.autonArms.right.grabStone()
        robot.autonArms.right.liftStone()
        val firstLeftStrafe = when (location) {
            LEFT -> 8.5
            MIDDLE -> 5.0
            RIGHT -> 5.5
        }
        robot.chassis.encoders.moveLeftInches(firstLeftStrafe)
        val toFoundationDistance = when (location) {
            LEFT -> 66.5
            MIDDLE -> 72.0
            RIGHT -> 80.0
        }
        robot.chassis.encoders.moveVertical(toFoundationDistance, 0.8)
        robot.chassis.turnTo(90.0)
        wait(500)
        val rightToFoundation = when (location) {
            LEFT -> 8.0
            MIDDLE -> 9.0
            RIGHT -> 9.0
        }
        robot.chassis.encoders.moveRightInches(rightToFoundation)
        robot.chassis.turnTo(90.0)
        robot.autonArms.right.dropStone()
        robot.autonArms.right.raiseArm()
        wait(1000)
        robot.chassis.encoders.moveLeftInches(9.0)
        robot.chassis.turnTo(90.0)

        val firstBackwards = when (location) {
            LEFT -> 98.0
            MIDDLE -> 101.0
            RIGHT -> 105.0
        }
        robot.chassis.encoders.moveBackwardInches(firstBackwards, 0.8)
        val rightStrafe = when (location) {
            LEFT -> 3.0
            MIDDLE -> 3.0
            RIGHT -> 3.5
        }
        robot.chassis.encoders.moveRightInches(rightStrafe)
        robot.autonArms.right.grabStone()
        robot.chassis.stop()
    }

    private fun getSkyStoneLocation(cropRectangle: Any): SkyStoneLocation {
//        val bm = robot.vuforia.getCroppedBitmap(AutonConstants.Skystone.SKYSTONE_CROP)!!
        return getSkyStoneLocationFromBitmap(robot.vuforia.getBitmap()!!)
    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply {
            useVuforia = true
            useSlam = true
        }
    }
    //    override fun getInitialSlam(): Point {
    //        return super.getInitialSlam()
    //    }
}
