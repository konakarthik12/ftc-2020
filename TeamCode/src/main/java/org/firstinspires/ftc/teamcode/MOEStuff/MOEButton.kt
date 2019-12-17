package org.firstinspires.ftc.teamcode.MOEStuff

import android.util.Log

class MOEButton(private val get: () -> Boolean) {
    //    private var wasPressed: Boolean = false
    //    set(value)
    //    {
    //        field = value
    //        //            Log.e("new value is", field.toString())
    //        listeners.forEach { it(value) }
    //    }

    var isToggled: Boolean = false
    var isPressed: Boolean = false
        set(value) {
            if (field == value) return
            field = value
            if (value) isToggled = !isToggled
            listeners.forEach { it(value) }
        }
    private var listeners = ArrayList<(Boolean) -> Unit>()
    fun update() {
        isPressed = get()
        //        if (isPressed && !wasPressed) {
        //            wasPressed = true
        //            isToggled = !isToggled
        //        }
        //        if (wasPressed && !isPressed) {
        //            wasPressed = false
        //        }

    }

    fun onKey(function: (currentState: Boolean) -> Unit) {
        listeners.add(function)
    }

    fun onKeyUp(function: () -> Unit) {
        onKey {
            if (!it) {
                function()
            }
        }
    }

    fun onKeyDown(function: () -> Unit) {
        onKey {
            if (it) {
                function()
            }
        }
    }


}
