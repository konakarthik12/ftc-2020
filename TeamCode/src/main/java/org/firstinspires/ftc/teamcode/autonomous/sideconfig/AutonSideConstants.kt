package org.firstinspires.ftc.teamcode.autonomous.sideconfig

import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle

object AutonSideConstants {
    val blue = AutonSideConfig(
            listOf(
                    //move backwards towards skystones
                    MovementOptions(26.0),
                    //align with skystone
                    MovementOptions(1.5, -1.75, -8.0),
                    //strafe towards skystone
                    MovementOptions(4.5, 1.0, 1.5),
                    //strafe away from skystone
                    MovementOptions(8.5, 5.0, 5.5)
                    //

            ),
            Rectangle(322, 234, 453, 37)
    )
}