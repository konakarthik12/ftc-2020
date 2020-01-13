package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEPatrickSlamConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.distanceFrom
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.getRelativePoint

class MOEPatrickTrans(val config: MOEPatrickSlamConfig) {
    val initialCameraTrans = findInitialCameraTrans()
    fun findInitialCameraTrans(): Transformation {
        val radTheta = config.robotInitial.radAng + config.ROBOT_TO_CAMERA_DIST.radAng
        val pose = config.robotInitial.pose.getRelativePoint(config.ROBOT_TO_CAMERA_DIST.r, radTheta)
        val angle = config.robotInitial.degAng + config.ROBOT_TO_CAMERA_THETA
        return Transformation(pose,angle)
    }

//    fun getTrans(slamRawPose: Transformation): Transformation {
//
//    }
}

fun main() {
    val moePatrickTrans = MOEPatrickTrans(MOEPatrickSlamConfig(robotInitial = Transformation(10.0, 10.0, 45.0)))
//    println(moePatrickTrans.toStriqazwsxedcrfng())
}