package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.PolarPoint

data class MOESlamConfig(
        var robotInitial: Transformation = Transformation(),

        var CAMERA_TO_ROBOT_DIST: PolarPoint = PolarPoint(0.0, 0.0),
        var ROBOT_TO_CAMERA_THETA: Double = 180.0
)

data class MOEPatrickSlamConfig(
        var robotInitial: Transformation = Transformation(),
        var ROBOT_TO_CAMERA_DIST: PolarPoint = PolarPoint(18.027756, 176.82016980),
        var ROBOT_TO_CAMERA_THETA: Double = 180.0

) {

}