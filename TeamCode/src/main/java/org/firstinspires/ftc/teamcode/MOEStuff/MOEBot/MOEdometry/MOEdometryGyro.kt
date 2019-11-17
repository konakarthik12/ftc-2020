package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEGyro

class MOEdometryGyro : MOEGyro() {
    override fun init(sync: Boolean) {
    }

    private var theta = 0.0

    private val degrees: Double
        get() = theta * 360


    override fun getRawAngle(): Double = degrees

    fun update(angleDifference: Double) {
        theta = (theta + angleDifference) % 1
    }

}
