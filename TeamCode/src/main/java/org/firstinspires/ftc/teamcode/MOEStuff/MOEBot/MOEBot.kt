package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEChassis
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOESlamGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlam

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem

class MOEBot(useGyro: Boolean = true,
             useCamera: Boolean = false,
             val useSlam: Boolean = false) {


    constructor(config: MOEBotConfig) : this(config.useGyro, config.useCamera, config.useSlam)

    val lift = MOELift()
    val foundation = MOEFoundation()
    val outTake = MOEOutTake()
    var chassis: MOEChassis = MOEChassis()
    var harvester: MOEHarvester = MOEHarvester()
    var purePursuit: MOEPurePursuitHandler = MOEPurePursuitHandler()
    lateinit var gyro: MOEGyro
    lateinit var vuforia: MOEVuforia
    lateinit var camera: MOECamera
    lateinit var slam: MOESlam

    init {
        if (useGyro) {
            gyro = (if (useSlam) MOESlamGyro() else MOEIMUGyro())
        }
        if (useCamera) {
            camera = MOECamera()
            vuforia = MOEVuforia()
        }
        if (useSlam) slam = MOESlam()
    }

    fun offsetValues(constants: MOEBotConstants) {
        if (useSlam) slam.config = constants.getSlamConfig()
        gyro.config = constants.getGyroConfig()

    }

    //    fun resetValues() {
    //        if (useSlam)
    //            slam.resetValues(thetaOffset)
    //        gyro.setToZero()
    //    }
}