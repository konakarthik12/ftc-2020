package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

data class PolarPoint(val r: Double, val radAng: Double) {
    val degAng: Double
        get() = radAng.toDegrees()
}