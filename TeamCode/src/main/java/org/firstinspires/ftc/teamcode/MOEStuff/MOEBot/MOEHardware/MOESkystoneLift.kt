package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEHardware

val stoneHeight = 600.0
val initialHeight = 600.0

class MOESkystoneLift : MOELift() {
    var target = 0.0
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
