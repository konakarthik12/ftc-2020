package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.MOEdometrySystem
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEOpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOESlam.MOESlam

class MOEBot(opMode: MOEOpMode, useCamera: Boolean = false, useSlam: Boolean = false) {
    var chassis: MOEChassis = MOEChassis()
    var odometry: MOEdometrySystem = MOEdometrySystem()
    var gyro: MOEIMUGyro = MOEIMUGyro()
    lateinit var camera: MOECamera
    lateinit var slam: MOESlam

    init {
        if (useCamera) camera = MOECamera(opMode)
        if (useSlam) slam = MOESlam()
    }
}