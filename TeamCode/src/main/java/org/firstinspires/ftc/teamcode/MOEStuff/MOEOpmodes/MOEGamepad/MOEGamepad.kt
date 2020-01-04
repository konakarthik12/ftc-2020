package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEGamepad

import com.qualcomm.robotcore.hardware.Gamepad
import kotlin.math.atan2
import kotlin.math.hypot

class MOEGamepad(val gamepad: Gamepad) {
    init {
        gamepad.setJoystickDeadzone(0.05F)
    }

    val x = MOEButton { gamepad.x }
    val y = MOEButton { gamepad.y }
    val a = MOEButton { gamepad.a }
    val b = MOEButton { gamepad.b }
    val buttons = arrayOf(x, y, a, b)

    val dpad = MOEDpad(
            { gamepad.dpad_up },
            { gamepad.dpad_down },
            { gamepad.dpad_left },
            { gamepad.dpad_right }
    )

    val left = MOEGamepadSide({ gamepad.left_stick_x }, { gamepad.left_stick_y }, { gamepad.left_trigger }, { gamepad.left_bumper })
    val right = MOEGamepadSide({ gamepad.right_stick_x }, { gamepad.right_stick_y }, { gamepad.right_trigger }, { gamepad.right_bumper })


    fun update() {
        buttons.forEach { it.update() }
        dpad.update()
        left.update()
        right.update()
    }


    val trigger_diff
        get() = (gamepad.right_trigger - gamepad.left_trigger).toDouble()
}
