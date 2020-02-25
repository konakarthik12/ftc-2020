package org.firstinspires.ftc.teamcode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation.*
import org.firstinspires.ftc.teamcode.autonomous.vision.getSkyStoneLocationFromBitmap
import org.firstinspires.ftc.teamcode.utilities.internal.wait

@Autonomous
class BlueAuton : MOEAuton() {
    override fun initOpMode() {
//        robot.autonArms.right.raiseArm()
//        robot.autonArms.right.openClaw()
        robot.autonArms.initAutonArms()
        robot.autonArms.left.armServo.setPosition(.17)
        robot.foundation.moveUp()
    }

    override fun run() {
        val location = getSkyStoneLocation()
        telemetry.addData("location", location)
        telemetry.addData("gyro", robot.gyro.angle)
        telemetry.update()
        //FIRST MOVEMENT TOWRADS STONE
        robot.chassis.encoders.moveBackwardInches(28.0)

        robot.chassis.turnTo(90.0)
        val verticalDistance = when (location) {
            LEFT -> 1.5
            MIDDLE -> -1.75
            RIGHT -> -9.5
        }
        telemetry.addData("distance", verticalDistance)
        telemetry.update()
        robot.chassis.encoders.moveVertical(verticalDistance)
        val strafeDistance = when (location) {
            LEFT -> 5.5
            MIDDLE -> 3.0
            RIGHT -> 3.5
        }
        robot.chassis.turnTo(90.0)
        robot.chassis.encoders.moveRightInches(strafeDistance)
        wait(500)
        robot.autonArms.right.grabStone()
        robot.autonArms.right.liftStone()
        val strafeOffDistance = when (location) {
            LEFT -> 5.0
            MIDDLE -> 6.0
            RIGHT -> 4.0
        }
        robot.chassis.encoders.moveLeftInches(strafeDistance + strafeOffDistance)
        val toFoundationDistance = when (location) {
            LEFT -> 66.5
            MIDDLE -> 80.0
            RIGHT -> 88.0
        }
        robot.chassis.encoders.moveVertical(toFoundationDistance, 0.8)
        robot.chassis.turnTo(90.0)
        val toFoundationDistanceStrafe = when (location) {
            LEFT -> 5.5
            MIDDLE -> 9.5
            RIGHT -> 9.0
        }
        robot.chassis.encoders.moveRightInches(toFoundationDistanceStrafe)
        robot.chassis.turnTo(90.0)
        robot.autonArms.right.dropStone()
        robot.autonArms.right.raiseArm()
        robot.autonArms.right.initAutonArm()
        //wait(1000)
        val toFoundationDistanceStrafeOff = when (location) {
            LEFT -> 3.0
            MIDDLE -> 1.5
            RIGHT -> 1.5
        }
        robot.chassis.encoders.moveLeftInches(toFoundationDistanceStrafe+toFoundationDistanceStrafeOff)
        robot.chassis.turnTo(90.0)

        val firstBackwards = when (location) {
            LEFT -> 100.0
            MIDDLE -> 110.0
            RIGHT -> 118.0
        }
        robot.chassis.encoders.moveBackwardInches(firstBackwards)
        robot.chassis.turnTo(90.0)
        val rightStrafe = when (location) {
            LEFT -> 4.5
            MIDDLE -> 5.5
            RIGHT -> 9.0
        }
        robot.chassis.encoders.moveRightInches(rightStrafe)
        robot.autonArms.right.grabStone()
        robot.autonArms.right.liftStone()
        val leftStrafeOff = when (location) {
            LEFT -> 0.0
            MIDDLE -> 0.0
            RIGHT -> 0.0
        }
        robot.chassis.encoders.moveLeftInches(rightStrafe + leftStrafeOff)
        val toFoundationDistance2 = when (location) {
            LEFT -> 115.0
            MIDDLE -> 120.0
            RIGHT -> 130.0
        }
        robot.chassis.encoders.moveVertical(toFoundationDistance2, 0.8)
        robot.chassis.turnTo(90.0)
        val toFoundationDistanceStrafe2 = when (location) {
            LEFT -> 6.5
            MIDDLE -> 9.5
            RIGHT -> 9.0
        }
        robot.chassis.encoders.moveRightInches(toFoundationDistanceStrafe2)
        robot.chassis.turnTo(90.0)
        robot.autonArms.right.dropStone()
        robot.autonArms.right.raiseArm()
        robot.autonArms.right.initAutonArm()

        robot.chassis.stop()
    }

    private fun getSkyStoneLocation(): SkyStoneLocation {
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
