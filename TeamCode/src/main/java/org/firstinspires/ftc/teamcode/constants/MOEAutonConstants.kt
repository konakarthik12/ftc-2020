package org.firstinspires.ftc.teamcode.constants

import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.AutonConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.reflectAutonConfig
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle

object MOEAutonConstants {

        val Left = AutonConfig(
                skystoneCropRect = Rectangle(0, 0, 0, 0),
                robotToFieldOffset = 90.0,
                positionOffsets = Point(0.0, 0.0), // Point(15.0, 96.0),
                topSkystonePosition = Point(96.0 + 4.0, 96.0 - 8.0),
                afterSkystonePosition = Point(48.0, 96.0),
                dumpSkystonePosition = Point(48.0, 192.0),
                parkPosition = Point(63.0, 144.0)
        )
        val Right = reflectAutonConfig(Left)
}