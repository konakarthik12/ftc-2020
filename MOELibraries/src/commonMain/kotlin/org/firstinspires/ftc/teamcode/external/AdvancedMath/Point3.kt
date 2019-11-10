package org.firstinspires.ftc.teamcode.external.AdvancedMath

import org.firstinspires.ftc.teamcode.external.format


class Point3(val x: Double, val y: Double, val z: Double) {
    constructor(it: List<Double>) : this(it[0], it[1], it[2])

    override fun toString(): String {
        return "Point [x=${x.format(3)}, y=${y.format(3)}, z=${z.format(3)}]"
    }
}




