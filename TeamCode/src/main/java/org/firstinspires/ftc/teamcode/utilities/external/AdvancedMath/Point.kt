package org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath

import org.firstinspires.ftc.teamcode.utilities.external.toPrecision

open class Point(override var x: Double, override var y: Double) : PointImpl<Point> {




    override fun toString(): String {
        return "Point [x=${x.toPrecision(3)}, y=${y.toPrecision(3)}]"
    }

    override fun create(x: Double, y: Double): Point {
        return Point(x, y)
    }


}

