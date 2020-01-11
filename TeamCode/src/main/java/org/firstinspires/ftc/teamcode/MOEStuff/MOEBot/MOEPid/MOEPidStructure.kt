package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder

interface MOEPidStructure<I, O> {
//    var lastValue: T

    var input: () -> I
    var output: ((O) -> Unit)
    var setpoint: () -> I
    var endCondition: (I) -> Boolean
    fun run(sync: Boolean = true): Job {
        return GlobalScope.launch {
            while (ReferenceHolder.moeOpMode.iOpModeIsActive()) {
                val curInput = input()
                if (endCondition(curInput)) {
                    break
                }
                output(getOutput(curInput, setpoint()))
            }
        }.also {
            if (sync) {
                runBlocking { it.join() }
            }
        }
    }

//    fun getOutput(input: I): O
    fun getOutput(input: I, setpoint: I): O

}
