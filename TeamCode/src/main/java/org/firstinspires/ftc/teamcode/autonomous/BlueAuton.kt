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
        //FROM WALL TO STONES
        robot.chassis.encoders.moveBackwardInches(28.0)
        robot.chassis.turnTo(90.0)
        //GRAB 1ST SKYSTONE
        moveSkyStoneVertical(location,1.5,-1.75,-9.5)
        wait(300)
        moveSkyStoneRight(location, 5.5,3.0,3.5)
        robot.autonArms.right.grabStone()
        robot.autonArms.right.liftStone()
        moveSkyStoneLeft(location, 11.5, 9.0, 7.5)
        robot.chassis.turnTo(90.0)
        //PLACE 1ST STONE ON FOUNDATION
        moveSkyStoneVertical(location, 66.5,80.0,88.0)
        wait(300)
        moveSkyStoneRight(location, 5.5,9.5,9.0)
        robot.autonArms.right.dropStone()
        robot.autonArms.right.raiseArm()
        robot.autonArms.right.initAutonArm()
        robot.chassis.turnTo(90.0)
        moveSkyStoneLeft(location, 8.5,11.0,10.5)
        //GRAB 2ND SKYSTONE
        robot.chassis.turnTo(90.0)
        moveSkyStoneVertical(location, -100.0,-110.0,118.0)
        wait(300)
        moveSkyStoneRight(location, 4.5,5.5,9.0)
        robot.autonArms.right.grabStone()
        robot.autonArms.right.liftStone()
        moveSkyStoneLeft(location, 4.5,5.5,9.0)
        robot.chassis.turnTo(90.0)
        //PLACE 2ND STONE ON FOUNDATION
        moveSkyStoneVertical(location, 108.0,118.0,126.0)
        wait(300)
        moveSkyStoneRight(location, 6.5,9.5,9.0)
        robot.autonArms.right.dropStone()
        robot.autonArms.right.raiseArm()
        robot.autonArms.right.initAutonArm()
        robot.chassis.turnTo(90.0)
        moveSkyStoneLeft(location, 6.5, 9.5, 9.0)
        robot.chassis.turnTo(90.0)
        //PARK
        moveSkyStoneVertical(location, -20.0,-20.0,-20.0)

        robot.chassis.stop()
    }

    private fun moveSkyStoneVertical(location: SkyStoneLocation, leftD: Double, middleD: Double, rightD: Double){
        robot.chassis.encoders.moveVertical(when (location){
            LEFT -> leftD
            MIDDLE -> middleD
            RIGHT -> rightD
        })
    }
    private fun moveSkyStoneRight(location: SkyStoneLocation, leftD: Double, middleD: Double, rightD: Double){
        robot.chassis.encoders.moveRightInches(when (location){
            LEFT -> leftD
            MIDDLE -> middleD
            RIGHT -> rightD
        })
    }
    private fun moveSkyStoneLeft(location: SkyStoneLocation, leftD: Double, middleD: Double, rightD: Double){
        robot.chassis.encoders.moveLeftInches(when (location){
            LEFT -> leftD
            MIDDLE -> middleD
            RIGHT -> rightD
        })
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
