package org.firstinspires.ftc.teamcode.constants

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.PolarPoint

class MOESlamConstants {
    companion object {
        val DefaultValues = MOESlamConfig(
                ROBOT_TO_CAMERA_DIST = PolarPoint(18.027756, 176.82016980),
                ROBOT_TO_CAMERA_THETA = 180.0
        )
    }
}