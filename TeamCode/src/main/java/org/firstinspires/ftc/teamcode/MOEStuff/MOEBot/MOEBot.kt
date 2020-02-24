package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEAutonArm.MOEAutonArms
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEChassis
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOESlamGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEdometryGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOEIntake
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOEOuttake
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware.MOESkystoneLift
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV.MOEPenCV
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlam
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem

class MOEBot(config: MOEBotConstantsImpl) {
    val autonArms = MOEAutonArms()
    val robotConfig = config.getRobotSubSystemConfig()

//    constructor(config: MOEBotConstants) : this(config.getRobotConfig().useGyro, config.getRobotConfig().useCamera, config.useSlam)

    val lift = MOESkystoneLift()
    val foundation = MOEFoundation()
    val outtake = MOEOuttake()
    var chassis: MOEChassis = MOEChassis()
    var intake: MOEIntake = MOEIntake()
    var odometry = MOEdometrySystem(config)
    lateinit var gyro: MOEGyro
    lateinit var vuforia: MOEVuforia
    lateinit var slam: MOESlam
    lateinit var opencv: MOEPenCV

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
        if (robotConfig.useOpenCV && robotConfig.useVuforia) throw IllegalStateException("Can't use both opencv and vuforia")
        if (robotConfig.useOpenCV) {
            opencv = MOEPenCV(config.getOpenCVConfig())
        } else if (robotConfig.useVuforia) {
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
        if (robotConfig.useOpenCV) opencv.stop()
    }

    //    fun resetValues() {
    //        if (useSlam)
    //            slam.resetValues(thetaOffset)
    //        gyro.setToZero()
    //    }
}