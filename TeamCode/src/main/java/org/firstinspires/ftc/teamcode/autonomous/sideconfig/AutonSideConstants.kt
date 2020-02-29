package org.firstinspires.ftc.teamcode.autonomous.sideconfig

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Direction.*
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Rectangle

object AutonSideConstants {


    val blue = AutonSideConfig(
            movements = listOf(
                    // 0 - move from wall to stones
                    MovementOptions(Backwards, 28.0),
                    //1 - move to align with skystone
                    MovementOptions(Vertical, 2.0, -2.0, -11.0),
                    //2 - strafe towards skystone
                    MovementOptions(Right, 4.5, 4.0, 3.5),
                    //3 - strafe away from skystone
                    MovementOptions(Left, 9.0, 9.0, 7.5),
                    //4 - move towards foundation
                    MovementOptions(Forward, 66.5, 80.0, 88.0),
                    //5 - strafe towards foundation
                    MovementOptions(Right, 8.5, 9.5, 9.0),
                    //6 - strafe away from foundation
                    MovementOptions(Left, 8.5, 9.5, 10.5),
                    //7 - move backwards to second skystone
                    MovementOptions(Backwards, 97.0, 109.0, 117.0),
                    //8- move right towards second stone
                    MovementOptions(Right, 7.0, 8.0, 9.0),
                    //9- move left after grabbing second stone
                    MovementOptions(Left, 4.5, 4.5, 9.0),
                    //10- move forwards towards foundation
                    MovementOptions(Forward, 108.0, 115.0, 125.0),
                    //11- strafe right towards foundation
                    MovementOptions(Right, 10.5, 10.5, 11.0),
                    //12- strafe left away from foundation
                    MovementOptions(Left, 8.0, 10.5, 10.0),
                    //13- move backwards to park
                    MovementOptions(Backwards, 63.0, 64.0, 64.0)
            ),
            cropRectangle = Rectangle(420, 237, 290, 40),
            negateStuff = false
    )
    val red = blue.reflect(Rectangle(10, 200, 280, 34))
//    val red = AutonSideConfig(
//            movements = listOf(
//                    // 0 - move from wall to stones
//                    MovementOptions(Backwards, 28.0),
//                    //1 - move to align with skystone
//                    MovementOptions(Vertical, 2.0, -4.0, -11.0),
//                    //2 - strafe towards skystone
//                    MovementOptions(Left, 4.5, 4.0, 3.5),
//                    //3 - strafe away from skystone
//                    MovementOptions(Right, 9.0, 9.0, 7.5),
//                    //4 - move towards foundation
//                    MovementOptions(Forward, 66.5, 80.0, 88.0),
//                    //5 - strafe towards foundation
//                    MovementOptions(Left, 8.5, 9.5, 9.0),
//                    //6 - strafe away from foundation
//                    MovementOptions(Right, 8.5, 9.5, 10.5),
//                    //7 - move backwards to second skystone
//                    MovementOptions(Backwards, 97.0, 109.0, 117.0),
//                    //8- move right towards second stone
//                    MovementOptions(Left, 7.0, 8.0, 9.0),
//                    //9- move left after grabbing second stone
//                    MovementOptions(Right, 4.5, 4.5, 9.0),
//                    //10- move forwards towards foundation
//                    MovementOptions(Forward, 108.0, 115.0, 125.0),
//                    //11- strafe right towards foundation
//                    MovementOptions(Left, 10.5, 10.5, 11.0),
//                    //12- strafe left away from foundation
//                    MovementOptions(Right, 8.0, 10.5, 10.0),
//                    //13- move backwards to park
//                    MovementOptions(Backwards, 63.0, 64.0, 64.0)
//            ),
//            cropRectangle = Rectangle(10, 200, 280, 34),
//            flipSkystoneIndex = true,
//            negateStuff=true
//    )
}