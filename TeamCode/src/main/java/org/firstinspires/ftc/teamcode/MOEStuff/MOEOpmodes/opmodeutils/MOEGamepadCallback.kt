package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils

//package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils
//
//import android.util.ArrayMap
//import com.qualcomm.robotcore.hardware.Gamepad
//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
//
//class MOEGamepadCallback(var moePad: MOEGamePad) : Gamepad.GamepadCallback {
//    var oldState = moePad.getButtonState()
//
//    @Synchronized
//    override fun gamepadChanged(gamepad: Gamepad) {
//        val newState = moePad.getButtonState()
//        callChanges(oldState, newState)
//        oldState = newState
//    }
//
//    private fun callChanges(oldState: ArrayMap<Button, Boolean>, newState: ArrayMap<Button, Boolean>) {
//        for ((key, value) in newState) {
//            val b = oldState[key]
//            if (b != null && b != value) {
//                moePad.callListener(key, b)
//            }
//        }
//    }
//}
//
