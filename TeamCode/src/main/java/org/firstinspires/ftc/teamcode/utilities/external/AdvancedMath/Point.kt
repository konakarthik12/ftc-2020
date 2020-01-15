package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import org.firstinspires.ftc.teamcode.utilities.external.toFixed

open class Point(override var x: Double, override var y: Double) : PointImpl<Point> {


    override fun toString(): String {
        return "Point [x=${x.toFixed(3)}, y=${y.toFixed(3)}]"
    }

    override fun create(x: Double, y: Double): Point {
        return Point(x, y)
    }

    override fun clone(): Point = create(x, y)


}

