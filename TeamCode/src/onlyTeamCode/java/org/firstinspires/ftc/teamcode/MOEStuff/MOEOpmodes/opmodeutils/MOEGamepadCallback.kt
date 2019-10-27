package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils

import android.util.ArrayMap
import com.qualcomm.robotcore.hardware.Gamepad

class MOEGamepadCallback : Gamepad.GamepadCallback {
    val oldState = ArrayMap<String, Boolean>()

    override fun gamepadChanged(gamepad: Gamepad) {

    }
}

