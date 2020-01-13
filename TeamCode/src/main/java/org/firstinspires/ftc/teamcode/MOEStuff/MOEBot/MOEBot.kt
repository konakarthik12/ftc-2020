package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEChassis
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOESlamGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlam

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem

class MOEBot(config: MOEBotConstants) {
    val robotConfig = config.getRobotConfig()

//    constructor(config: MOEBotConstants) : this(config.getRobotConfig().useGyro, config.getRobotConfig().useCamera, config.useSlam)

    val lift = MOELift()
    val foundation = MOEFoundation()
    val outtake = MOEOuttake()
    var chassis: MOEChassis = MOEChassis()
    var harvester: MOEHarvester = MOEHarvester()

    lateinit var gyro: MOEGyro
    lateinit var vuforia: MOEVuforia
    lateinit var camera: MOECamera
    lateinit var slam: MOESlam

    init {
        if (robotConfig.useGyro) {
            gyro = (if (robotConfig.useSlam) MOESlamGyro() else MOEIMUGyro())
        }
        if (robotConfig.useCamera) {
            camera = MOECamera()
            vuforia = MOEVuforia()
        }
        if (robotConfig.useSlam) slam = MOESlam(config.getSlamConfig())
    }

    fun offsetValues(constants: MOEBotConstants) {
        if (robotConfig.useSlam) {
//            slam.config = constants.getSlamConfig()
            slam.resetValues()
        }
        gyro.config = constants.getGyroConfig()

    }

    //    fun resetValues() {
    //        if (useSlam)
    //            slam.resetValues(thetaOffset)
    //        gyro.setToZero()
    //    }
}