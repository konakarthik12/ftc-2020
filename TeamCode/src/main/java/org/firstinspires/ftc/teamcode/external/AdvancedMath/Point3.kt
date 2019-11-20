package org.firstinspires.ftc.teamcode.external.AdvancedMath

class Point3(val x: Double, val y: Double, val z: Double) {
    constructor(it: List<Double>) : this(it[0], it[1], it[2])

    override fun toString(): String {
        //TODO: Sometime
        throw NotImplementedError();
        //        return "Point [x=$x, y=$y}, z=${z.format(3)}]"
    }
}