package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import org.firstinspires.ftc.teamcode.constants.MOEConstants

class WheelsHolder {
    // axial = forward/backward motion
    // strafe = left/right motion
    val axialRight = MOEdometryWheel(MOEConstants.Odometry.Wheels.Configs.axialRight)
    val axialLeft = MOEdometryWheel(MOEConstants.Odometry.Wheels.Configs.axialLeft)
    val strafeRight = MOEdometryWheel(MOEConstants.Odometry.Wheels.Configs.strafeRight)

    fun update() {
        axialLeft.update()
        axialRight.update()
        strafeRight.update()
    }
}