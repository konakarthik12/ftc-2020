package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBotConstantsImpl
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toNormalAngle


class MOESlamGyro(val botConstants: MOEBotConstantsImpl) : MOEGyro() {
//    init {
//        userSetOffset = botConstants.getRobotInitialState().initialTrans.degAng
//    }

    override fun getRawEulerAngle(): Double = -robot.slam.getRawTheta()

    override fun getRawAngle(): Double = getRawEulerAngle().toNormalAngle()
}

