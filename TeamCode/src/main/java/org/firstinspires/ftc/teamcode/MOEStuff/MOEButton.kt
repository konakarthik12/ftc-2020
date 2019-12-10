package org.firstinspires.ftc.teamcode.MOEStuff

class MOEButton(private val get: () -> Boolean) {
    private var wasPressed: Boolean = false
    var isToggled: Boolean = false
    var isPressed: Boolean = false

    fun update() {
        isPressed = get()
        if (isPressed && !wasPressed){
            wasPressed = true
            isToggled = !isToggled
        }
        if (wasPressed && !isPressed){
            wasPressed = false
        }
    }
}
