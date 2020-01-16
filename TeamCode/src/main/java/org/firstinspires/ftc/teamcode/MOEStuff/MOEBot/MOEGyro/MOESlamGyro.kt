package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBotConstants
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.MOESlamConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toNormalAngle


class MOESlamGyro(val botConstants: MOEBotConstants) : MOEGyro() {
    init {
        userSetOffset = botConstants.getSlamConfig().robotInitial.degAng
    }

    override fun getRawEulerAngle(): Double = -robot.slam.getRawTheta()

    override fun getRawAngle(): Double = getRawEulerAngle().toNormalAngle()
}

