package org.firstinspires.ftc.teamcode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation.*
import org.firstinspires.ftc.teamcode.autonomous.vision.getSkyStoneLocationFromBitmap
import org.firstinspires.ftc.teamcode.utilities.internal.wait

@Autonomous
class AutonTemplate : MOEAuton() {
    override fun initOpMode() {
        robot.autonArms.right.raiseArm()
        robot.autonArms.right.openClaw()
        robot.foundation.moveUp()
    }

    override fun run() {
        val location = getSkyStoneLocation()
        telemetry.addData("location", location)
        telemetry.addData("gyro", robot.gyro.angle)
        telemetry.update()

        robot.chassis.encoders.moveBackwardInches(26.0)

        robot.chassis.turnTo(90.0)
        val verticalDistance = when (location) {
            LEFT -> 1.5
            MIDDLE -> -1.5
            RIGHT -> -9.5
        }
        robot.chassis.encoders.moveVertical(verticalDistance)
        val strafeDistance = when (location) {
            LEFT -> 4.5
            MIDDLE -> 2.0
            RIGHT -> 1.5
        }
        robot.chassis.encoders.moveRightInches(strafeDistance)

        wait(1500)
        robot.autonArms.right.lowerArm()

        wait(500)
        robot.autonArms.right.closeClaw()
        wait(750)
        robot.autonArms.right.raiseArm()
        wait(500)

        robot.chassis.encoders.moveLeftInches(strafeDistance + 4.0)
        robot.chassis.turnTo(90.0)
        robot.chassis.encoders.moveVertical(67.0 - verticalDistance)

        robot.chassis.encoders.moveRightInches(9.0)
        robot.chassis.turnTo(90.0)
        robot.autonArms.right.lowerArm()
        wait(500)
        robot.autonArms.right.openClaw()
        wait(1000)
        robot.autonArms.right.raiseArm()
        wait(1000)

//        val currentGyro = robot.gyro.angle;
//        val target = (currentGyro + 90).toNormalAngle()
//        telemetry.addData("angle", currentGyro)
//        telemetry.addData("target", target)
//        telemetry.update()
//        wait(2000)
//        robot.chassis.turnPower(0.5)
//        while (opModeIsActive() && robot.gyro.angle < target) {
//            telemetry.addData("angle", robot.gyro.angle)
//            telemetry.update()
//        }
        robot.chassis.stop()
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

//        robot.intake.setPower(0.6)
//        robot.chassis.encoders.moveForwardInches(30.0, 0.5)
//        robot.chassis.encoders.moveBackwardInches(35.0, 0.5) // Adding 5 astars for good measure.
//
//        robot.chassis.moveTo(-1.0 /* foundation */, -1.0)
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
