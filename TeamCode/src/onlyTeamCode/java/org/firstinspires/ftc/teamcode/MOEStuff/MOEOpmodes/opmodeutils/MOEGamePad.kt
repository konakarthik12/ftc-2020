package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils

import android.util.ArrayMap
import com.qualcomm.robotcore.hardware.Gamepad
import java.lang.reflect.Field

val gamePadClass = Gamepad::class.java

class MOEGamePad(private val gamepad: Gamepad) {

    private var callbackEnabled: Boolean = false
    private val callback by lazy { MOEGamepadCallback(this) }
    private val listenerMap = ArrayMap<Button, ArrayList<(state: Boolean) -> Unit>>()
    private val buttonFields = ArrayMap<Button, Field>(6)
    private val callbackField = gamePadClass.getField("callback")

    enum class Button {
        DPAD_UP, DPAD_DOWN, DPAD_RIGHT, DPAD_LEFT,
        A, B, X, Y,
        GUIDE, START, BACK,
        RIGHT_BUMPER, LEFT_BUMPER, LEFT_STICK_BUTTON, RIGHT_STICK_BUTTON;

        fun getField(): Field = gamePadClass.getField(getFieldName())


        private fun getFieldName(): String = this.name.toLowerCase()


    }

    fun onButton(button: Button, listener: (state: Boolean) -> Unit) {
        onButton(button, listener, null)
    }

    fun onButtonPressed(button: Button, listener: () -> Unit) {
        onButton(button, { listener() }, true)
    }

    fun onButtonReleased(button: Button, listener: () -> Unit) {
        onButton(button, { listener() }, false)

    }

    private fun onButton(button: Button, listener: (state: Boolean) -> Unit, checkFor: Boolean?) {
        addFieldForButton(button)
        enableCallback()
        addListener(button) {
            when (checkFor) {
                null -> listener(it)
                it -> listener(it)
            }

        }
    }

    private fun addFieldForButton(button: Button) {
        try {
            val field = button.getField()
            buttonFields[button] = field
        } catch (e: NoSuchFieldException) {
            throw IllegalArgumentException("Gamepad does not have field: $button")
        }


    }

    private fun addListener(button: Button, listener: (state: Boolean) -> Unit) {
        listenerMap.getOrPut(button, { ArrayList(1) }).add(listener)
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

    fun getButtonState(): ArrayMap<Button, Boolean> {
        val state = ArrayMap<Button, Boolean>(buttonFields.size)
        for ((key, value) in buttonFields) {
            state[key] = value.get(gamepad) as Boolean
        }
        return state
    }

    fun callListener(key: Button, newState: Boolean) {
        listenerMap[key]?.forEach { it(newState) }
    }
}