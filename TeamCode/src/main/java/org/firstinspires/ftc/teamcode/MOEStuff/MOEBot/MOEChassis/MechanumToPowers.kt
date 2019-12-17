package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

data class Powers(val FLP: Double, val FRP: Double, val BLP: Double, val BRP: Double) {
    companion object {
        fun mechanumToPowers(fwd: Double, str: Double, rot: Double, maxPower: Double = 1.0): Powers {
            var FRP = fwd - str - rot
            var FLP = fwd + str + rot
            var BRP = fwd + str - rot
            var BLP = fwd - str + rot

            var max = if (FRP > maxPower) FRP else maxPower
            if (max < FRP) max = FRP
            if (max < BLP) max = BLP
            if (max < BRP) max = BRP
            if (max < FLP) max = FLP

            FLP /= max
            FRP /= max
            BLP /= max
            BRP /= max

            return Powers(FLP, FRP, BLP, BRP)
        }
    }
}

