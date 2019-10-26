package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import java.lang.Math.PI

class MOEdometryGyro(initialTheta: Double) {
    private var theta: Double = initialTheta

    val degrees: Double
        get() = theta * 360

    val radians: Double
        get() = theta * 2 * PI

    fun update(angleDifference: Double) {
        theta = (theta + angleDifference) % 1
    }
}
