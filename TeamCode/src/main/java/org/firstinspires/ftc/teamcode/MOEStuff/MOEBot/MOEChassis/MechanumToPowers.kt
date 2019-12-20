package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import kotlin.math.abs


data class Powers(var FLP: Double, var FRP: Double, var BLP: Double, var BRP: Double) {

    constructor(powers: List<Double>) : this(powers[0], powers[1], powers[2], powers[3])
    constructor(powers: Array<Double>) : this(powers[0], powers[1], powers[2], powers[3])

    companion object {

        fun mechanumToPowers(fwd: Double, str: Double, rot: Double, maxPower: Double = 1.0): Powers {
            var FLP = fwd + str + rot
            var FRP = fwd - str - rot
            var BLP = fwd - str + rot
            var BRP = fwd + str - rot
            val powers = arrayOf(FLP, FRP, BLP, BRP)
            var highestPower = abs(powers.maxBy { abs(it) }!!)

            if (highestPower > maxPower) {
                return Powers(powers.map { it * (maxPower / highestPower) })
            }
            return Powers(powers)
        }

    }

    operator fun timesAssign(num: Double) {
        FLP *= num
        FRP *= num
        BLP *= num
        BRP *= num
    }
}

