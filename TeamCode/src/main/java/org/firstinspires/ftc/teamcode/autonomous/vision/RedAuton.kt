package org.firstinspires.ftc.teamcode.autonomous.vision

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation.*
import org.firstinspires.ftc.teamcode.autonomous.vision.getSkyStoneLocationFromBitmap
import org.firstinspires.ftc.teamcode.utilities.internal.wait

@Autonomous
class RedAuton : MOEAuton() {
    override fun initOpMode() {
//        robot.autonArms.right.raiseArm()
//        robot.autonArms.right.openClaw()
        robot.autonArms.initAutonArms()
        robot.autonArms.left.armServo.setPosition(.17)
        robot.foundation.moveUp()
    }

    override fun run() {
        val location = getSkyStoneLocation()
        telemetry.addData("locatiob", location)
        telemetry.addData("gyro", robot.gyro.angle)
        telemetry.update()
        robot.autonArms.initAutonArms()

        robot.chassis.encoders.moveBackwardInches(26.0)

        robot.chassis.turnTo(-90.0)
        val verticalDistance = when (location) {
            LEFT -> 1.5
            MIDDLE -> -1.75
            RIGHT -> -8.5
        }
        telemetry.addData("distance", verticalDistance)
        telemetry.update()
        robot.chassis.encoders.moveVertical(verticalDistance)
        val strafeDistance = when (location) {
            LEFT -> 4.5
            MIDDLE -> 1.0
            RIGHT -> 1.5
        }
        robot.chassis.turnTo(-90.0)
        robot.chassis.encoders.moveLeftInches(strafeDistance)
        wait(1500)
        robot.autonArms.left.grabStone()
        robot.autonArms.left.liftStone()
        robot.chassis.encoders.moveRightInches(strafeDistance + 4.0)
        val toFoundationDistance = when (location) {
            LEFT -> 66.5
            MIDDLE -> 72.0
            RIGHT -> 80.0
        }
        robot.chassis.encoders.moveVertical(toFoundationDistance, 0.8)
        robot.chassis.turnTo(-90.0)
        robot.chassis.encoders.moveLeftInches(9.0)
        robot.chassis.turnTo(-90.0)
        robot.autonArms.left.dropStone()
        robot.autonArms.left.raiseArm()
        robot.autonArms.left.initAutonArm()
        //wait(1000)
        robot.chassis.encoders.moveRightInches(9.0)
        robot.chassis.turnTo(-90.0)

        val firstBackwards = when (location) {
            LEFT -> 98.0
            MIDDLE -> 101.0
            RIGHT -> 105.0
        }
        robot.chassis.encoders.moveBackwardInches(firstBackwards)
        val rightStrafe = when (location) {
            LEFT -> 3.0
            MIDDLE -> 3.0
            RIGHT -> 3.5
        }
        robot.chassis.encoders.moveLeftInches(rightStrafe)

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
