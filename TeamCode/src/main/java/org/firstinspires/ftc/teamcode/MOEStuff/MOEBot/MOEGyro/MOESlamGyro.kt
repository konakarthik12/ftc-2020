package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro

import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toNormalAngle


class MOESlamGyro : MOEGyro() {
    override fun getRawEulerAngle(): Double = -robot.slam.getRawTheta()
    override fun getRawAngle(): Double = getRawEulerAngle().toNormalAngle()
}

