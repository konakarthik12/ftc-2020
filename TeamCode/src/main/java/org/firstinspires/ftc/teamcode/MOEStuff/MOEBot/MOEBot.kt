package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEAutonArm.MOEAutonArm
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEAutonArm.MOEAutonArms
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEDometrySystem
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEChassis
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOESlamGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlam

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem

class MOEBot(config: MOEBotConstants) {
    val autonArms = MOEAutonArms()
    val robotConfig = config.getRobotSubSystemConfig()

//    constructor(config: MOEBotConstants) : this(config.getRobotConfig().useGyro, config.getRobotConfig().useCamera, config.useSlam)

    val lift = MOELift()
    val foundation = MOEFoundation()
    val outtake = MOEOuttake()
    var chassis: MOEChassis = MOEChassis()
    var intake: MOEIntake = MOEIntake()
    var odometry = MOEDometrySystem(config)
    lateinit var gyro: MOEGyro
    lateinit var vuforia: MOEVuforia
    lateinit var camera: MOECamera
    lateinit var slam: MOESlam

    init {
        if (robotConfig.useGyro) {
            gyro = (if (robotConfig.useSlam) MOESlamGyro(config) else MOEIMUGyro())
        }
        if (robotConfig.useCamera) {
            camera = MOECamera()
            vuforia = MOEVuforia()
        }
        if (robotConfig.useSlam) slam = MOESlam(config)
    }

    fun offsetValues(constants: MOEBotConstants) {
        if (robotConfig.useSlam) {
//            slam.config = constants.getSlamConfig()
            slam.resetValues()
        }
        if (robotConfig.useOdometry) {
            odometry.resetValues()
            odometry.launchLoop()
        }
        gyro.config = constants.getGyroConfig()


    }

    fun stop() {
        if (robotConfig.useOdometry) odometry.stop()
    }

    //    fun resetValues() {
    //        if (useSlam)
    //            slam.resetValues(thetaOffset)
    //        gyro.setToZero()
    //    }
}