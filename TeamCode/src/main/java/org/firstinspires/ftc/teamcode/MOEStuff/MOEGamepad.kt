package org.firstinspires.ftc.teamcode.MOEStuff

import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Gamepad as Constants

class MOEGamepad(private val gamepad: Gamepad) {
    val x = MOEButton { gamepad.x }
    val y = MOEButton { gamepad.y }
    val a = MOEButton { gamepad.a }
    val b = MOEButton { gamepad.b }
    val dpad_up = MOEButton { gamepad.dpad_up }
    val dpad_down = MOEButton { gamepad.dpad_down }
    val dpad_left = MOEButton { gamepad.dpad_left }
    val dpad_right = MOEButton { gamepad.dpad_right }
    val left_bumper = MOEButton { gamepad.left_bumper }
    val right_bumper = MOEButton { gamepad.right_bumper }
    val left_trigger_button = MOEButton { gamepad.left_trigger > Constants.ON_THRESHOLD }
    val right_trigger_button = MOEButton { gamepad.right_trigger > Constants.ON_THRESHOLD }
//    val back_button = MOEButton { gamepad.back }
    fun update() {
        buttons.forEach { it.update() }
    }

    val buttons = arrayOf(x, y, a, b, dpad_up, dpad_down, dpad_left, dpad_right, left_bumper, right_bumper)
    val left_stick_x
        get() = gamepad.left_stick_x

    val left_stick_y
        get() = gamepad.left_stick_y

    val right_stick_x
        get() = gamepad.right_stick_x

    val right_stick_y
        get() = gamepad.right_stick_y

    val left_trigger
        get() = gamepad.left_trigger

    val right_trigger
        get() = gamepad.right_trigger


}
