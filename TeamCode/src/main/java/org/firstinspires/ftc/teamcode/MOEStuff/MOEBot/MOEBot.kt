package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEAutonArm.MOEAutonArms
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEometrySystem
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEChassis
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEdometryGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOEIntake
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOELift
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOEOuttake

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem

class MOEBot(config: MOEBotConstantsImpl) {
    val autonArms = MOEAutonArms()
    val robotConfig = config.getRobotSubSystemConfig()

//    constructor(config: MOEBotConstants) : this(config.getRobotConfig().useGyro, config.getRobotConfig().useCamera, config.useSlam)

    val lift = MOELift()
    val foundation = MOEFoundation()
    val outtake = MOEOuttake()
    var chassis: MOEChassis = MOEChassis()
    var intake: MOEIntake = MOEIntake()
    var odometry = MOEometrySystem(config)
    lateinit var gyro: MOEGyro
    lateinit var vuforia: MOEVuforia
    lateinit var camera: MOECamera

    init {
        if (robotConfig.useGyro) {
            gyro = (if (robotConfig.useOdometry) MOEdometryGyro(config) else MOEIMUGyro())
        }
        if (robotConfig.useCamera) {
            camera = MOECamera()
            vuforia = MOEVuforia()
        }
    }

    fun offsetValues(constants: MOEBotConstantsImpl) {

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