package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEAutonArm.MOEAutonArms
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEChassis
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOESlamGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEdometryGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOEIntake
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOELift
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOEOuttake
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlam

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
    var odometry = MOEdometrySystem(config)
    lateinit var gyro: MOEGyro
    lateinit var vuforia: MOEVuforia
    lateinit var slam: MOESlam

    init {
        if (robotConfig.useSlam) {
            slam = MOESlam(config)
        }
        if (robotConfig.useGyro) {
            gyro = (when {
                robotConfig.useOdometry -> MOEdometryGyro(config)
                robotConfig.useSlam -> MOESlamGyro(config)
                else -> MOEIMUGyro()
            })
        }
        if (robotConfig.useVuforia) {

            vuforia = MOEVuforia(config.getVuforiaConfig())
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