package org.firstinspires.ftc.teamcode.utilities.external.other

class WrapperHandler(private val max: Int, val function: () -> Double) {
    var finalValue: Double = function()
    private var prev = finalValue
    private fun getDelta(curr: Double): Double {

        val ans = if (prev < curr) {
            val innerRange = curr - prev
            val outerRange = max - curr + prev
            if (innerRange < outerRange) innerRange else -outerRange
        } else {
            val innerRange = prev - curr
            val outerRange = max - prev + curr
            if (innerRange < outerRange) -innerRange else outerRange
        }
        finalValue = curr
        return ans
    }

    fun getValue(): Double {
        val curr = function()
        val delta = getDelta(curr)
        finalValue += delta
        prev = curr
        return finalValue
    }

}
