package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils

import android.util.ArrayMap
import com.qualcomm.robotcore.hardware.Gamepad
import java.lang.reflect.Field


class MOEGamePad(private val gamepad: Gamepad) {

    private var callbackEnabled: Boolean = false
    private val callback by lazy { MOEGamepadCallback(this) }
    private val listenerMap = ArrayMap<String, ArrayList<(state: Boolean) -> Unit>>().withDefault { ArrayList(1) }
    private val buttonFields = ArrayMap<String, Field>(6)
    private val gamePadClass = Gamepad::class.java
    private val callbackField = gamePadClass.getField("callback")

    fun onButton(button: String, listener: (state: Boolean) -> Unit) {
        onButton(button, listener, null)
    }

    private fun onButton(button: String, listener: (state: Boolean) -> Unit, checkFor: Boolean?) {
        addFieldForButton(button)
        enableCallback()
        addListener(button) {
            when (checkFor) {
                null -> listener(it)
                it -> listener(it)
            }

        }
    }

    private fun addFieldForButton(button: String) {
        try {
            val field = gamePadClass.getField(button.toLowerCase())
            buttonFields[button] = field
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException("Gamepad does not have field: $button")
        }


    }

    fun onButtonPressed(button: String, listener: () -> Unit) {
        onButton(button, { listener() }, true)
    }

    fun onButtonReleased(button: String, listener: () -> Unit) {
        onButton(button, { listener() }, false)

    }

    private fun addListener(button: String, listener: (state: Boolean) -> Unit) {
        listenerMap.getValue(button).add(listener)
    }


    private fun enableCallback() {
        if (callbackEnabled) return
        callbackEnabled = true
        reflectCallback()
    }

    private fun reflectCallback() {
        callbackField.isAccessible = true
        callbackField.set(gamepad, callback)
    }

    fun getButtonState(): ArrayMap<String, Boolean> {
        val state = ArrayMap<String, Boolean>(buttonFields.size)
        for ((key, value) in buttonFields) {
            state[key] = value.get(gamepad) as Boolean
        }
        return state
    }

    fun callListener(key: String, newState: Boolean) {
        listenerMap[key]?.forEach { it(newState) }
    }
}