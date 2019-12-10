package org.firstinspires.ftc.teamcode.MOEStuff

import com.qualcomm.robotcore.hardware.Gamepad

class MOEGamepad(private val gamepad: Gamepad) {
    val x: MOEButton = MOEButton { gamepad.x }
    val y: MOEButton = MOEButton { gamepad.y }
    val a: MOEButton = MOEButton { gamepad.a }
    val b: MOEButton = MOEButton { gamepad.b }
    val dpad_up: MOEButton = MOEButton { gamepad.dpad_up }
    val dpad_down: MOEButton = MOEButton { gamepad.dpad_down }
    val dpad_left: MOEButton = MOEButton { gamepad.dpad_left }
    val dpad_right: MOEButton = MOEButton { gamepad.dpad_right }
    val left_bumper: MOEButton = MOEButton { gamepad.left_bumper }
    val right_bumper: MOEButton = MOEButton { gamepad.right_bumper }

    val left_stick_x: Float
        get() = gamepad.left_stick_x

    val left_stick_y: Float
        get() = gamepad.left_stick_y

    val right_stick_x: Float
        get() = gamepad.right_stick_x

    val right_stick_y: Float
        get() = gamepad.right_stick_y

    val left_trigger: Float
        get() = gamepad.left_trigger

    val right_trigger: Float
        get() = gamepad.right_trigger

    fun update() {
        x.update()
        y.update()
        a.update()
        b.update()
        dpad_down.update()
        dpad_up.update()
        dpad_left.update()
        dpad_right.update()
        left_bumper.update()
        right_bumper.update()
    }
}
