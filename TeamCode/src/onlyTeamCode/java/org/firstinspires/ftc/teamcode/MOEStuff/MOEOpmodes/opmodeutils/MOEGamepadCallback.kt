package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils

import android.util.ArrayMap
import com.qualcomm.robotcore.hardware.Gamepad

class MOEGamepadCallback(var moePad: MOEGamePad) : Gamepad.GamepadCallback {


    var oldState = moePad.getButtonState()
    @Synchronized
    override fun gamepadChanged(gamepad: Gamepad) {
        val newState = moePad.getButtonState()
        callChanges(oldState, newState)
        oldState = newState
    }

    private fun callChanges(oldState: ArrayMap<String, Boolean>, newState: ArrayMap<String, Boolean>) {
        for ((key, value) in oldState) {
            val b = newState[key]
            if (b != null && b != value) {
                moePad.callListener(key, b)
            }
        }
    }
}

