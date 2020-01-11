package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import kotlin.math.abs

open class MOERawPid(options: MOEPidOptions) : MOEPidStructure<Double, Double> {


    var internalPid = MOEPid(options)

    override var input = { 0.0 }
    override var output: (Double) -> Unit = { }
    private var threshold = 0.1
    override var setpoint: () -> Double = { 0.0 }
    override var endCondition: (Double) -> Boolean = {
        abs(it - setpoint()) < threshold
    }


    fun setOptions(options: MOEPidOptions) {
        internalPid.P = options.P
        internalPid.I = options.I
        internalPid.D = options.D
        internalPid.F = options.F

    }

    fun getOutput(input: Double) = getOutput(input, internalPid.setpoint)
    override fun getOutput(input: Double, setpoint: Double): Double = internalPid.getOutput(input, setpoint)

    fun reset() = internalPid.reset()
    fun setOutputLimits(limit: Double) = internalPid.setOutputLimits(limit)

    constructor(P: Double = 0.0,
                I: Double = 0.0,
                D: Double = 0.0,
                F: Double = 0.0) : this(MOEPidOptions(P, I, D, F))
}
