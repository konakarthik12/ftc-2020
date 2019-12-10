package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOEIMUGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro.MOESlamGyro
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEOpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOESlam
import org.firstinspires.ftc.teamcode.constants.OpModeInterface

class MOEBot(opMode: OpModeInterface, useCamera: Boolean = false, val useSlam: Boolean = false,
             val thetaOffset: Double = 0.0) {
    val foundation = MOEFoundation()
    val outTake = MOEOutTake()
    var chassis: MOEChassis = MOEChassis()
    var harvester: MOEHarvester = MOEHarvester()
    var odometry: MOEdometrySystem = MOEdometrySystem()
    var purePursuit: MOEPurePursuitHandler = MOEPurePursuitHandler()
    var vuforia: MOEVuforia = MOEVuforia()
    var gyro: MOEGyro = if (useSlam) MOESlamGyro() else MOEIMUGyro()
    lateinit var camera: MOECamera
    lateinit var slam: MOESlam

    init {
        if (useCamera) camera = MOECamera(opMode)
        if (useSlam) slam = MOESlam()
    }

    fun resetValues() {
        if (useSlam)
            slam.resetValues(thetaOffset)
    }
}