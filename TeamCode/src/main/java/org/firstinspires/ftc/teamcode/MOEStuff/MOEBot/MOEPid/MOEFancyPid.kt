package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import kotlin.math.abs

open class MOEFancyPid(private var P: Double = 0.0,
                       I: Double = 0.0,
                       private var D: Double = 0.0,
                       private var F: Double = 0.0) : MOEPid(P, I, D, F) {

    var input: () -> Double = { 0.0 }

    lateinit var output: ((Double) -> Unit)
    private var threshold = 0.1
    var endCondition: (Double) -> Boolean = {
        abs(it - setpoint) < threshold
    }

    fun run(sync: Boolean = true): Job {
        return GlobalScope.launch {
            while (moeOpMode.iOpModeIsActive()) {
                val output1 = getOutput(input())
                output(output1)
                if (endCondition(output1)) {
                    break
                }
            }
        }.also {
            if (sync) {
                runBlocking { it.join() }
            }
        }
    }


    fun setOptions(options: MOEPidOptions) {
        this.P = options.P
        this.I = options.I
        this.D = options.D
        this.F = options.F

    }

    fun getOutput(): Double {
        return getOutput(input())
    }

    fun isFinished(): Boolean {
        return endCondition(input())
    }

}
