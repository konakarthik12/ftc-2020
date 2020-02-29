package org.firstinspires.ftc.teamcode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.autonomous.sideconfig.AutonSideConfig
import org.firstinspires.ftc.teamcode.autonomous.sideconfig.Movement
import org.firstinspires.ftc.teamcode.autonomous.vision.SkyStoneLocation
import org.firstinspires.ftc.teamcode.autonomous.vision.getSkyStoneLocationFromBitmap
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle
import org.firstinspires.ftc.teamcode.utilities.internal.wait

@Autonomous
open class AutonTemplate(val config: AutonSideConfig) : MOEAuton() {
    override fun initOpMode() {
//        robot.autonArms.right.raiseArm()
//        robot.autonArms.right.openClaw()
        robot.autonArms.initAutonArms()
        robot.autonArms.left.armServo.setPosition(.17)
        robot.foundation.moveUp()
    }

    override fun run() {
        val location = getSkyStoneLocation(config.cropRectangle)
        val distances = config.getDistances(location)
        telemetry.addData("location", location)
        telemetry.addData("gyro", robot.gyro.angle)
        telemetry.update()
        //FROM WALL TO STONES
        move(distances[0])
        robot.chassis.turnTo(90.0)
        //GRAB 1ST SKYSTONE
        move(distances[1])
        wait(300)
        move(distances[2])
        robot.autonArms.right.grabStone()
        robot.autonArms.right.liftStone()
        move(distances[3])
        robot.chassis.turnTo(90.0)
        //PLACE 1ST STONE ON FOUNDATION
        move(distances[4])
        wait(300)
        move(distances[5])
        robot.autonArms.right.dropStone()
        robot.autonArms.right.raiseArm()
        robot.autonArms.right.initAutonArm()
        robot.chassis.turnTo(90.0)
        move(distances[6])
        //GRAB 2ND SKYSTONE
        robot.chassis.turnTo(90.0)
        move(distances[7])
        wait(300)
        move(distances[8])
        robot.autonArms.right.grabStone()
        robot.autonArms.right.liftStone()
        move(distances[9])
        robot.chassis.turnTo(90.0)
        //PLACE 2ND STONE ON FOUNDATION
        move(distances[10])
        wait(300)
        move(distances[11])
        robot.autonArms.right.dropStone()
        robot.autonArms.right.raiseArm()
        robot.autonArms.right.initAutonArm()
        robot.chassis.turnTo(90.0)
        move(distances[12])
        robot.chassis.turnTo(90.0)
        //PARK
        move(distances[13])

        robot.chassis.stop()
    }

    private fun move(movement: Movement) {
        robot.chassis.encoders.move(movement)
    }

    private fun getSkyStoneLocation(cropRectangle: Rectangle): SkyStoneLocation {
//        val bm = robot.vuforia.getCroppedBitmap(AutonConstants.Skystone.SKYSTONE_CROP)!!
        return getSkyStoneLocationFromBitmap(robot.vuforia.getBitmap()!!, cropRectangle)
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
