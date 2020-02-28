package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware

import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot

val stoneHeight = 600.0
val initialHeight = 600.0

class MOESkystoneLift : MOELift() {
    var target = 0.0
        set(value) {
//            robot.lift.setTargetPosition(field.toInt())
            field = value
        }

    fun moveToInitial() {
        target = initialHeight
    }

    fun moveDownSkystones(i: Double) {
        target -= i * stoneHeight
    }

    fun moveUpSkystones(i: Double) {
        target += i * stoneHeight
    }

}
