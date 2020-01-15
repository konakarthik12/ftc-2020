package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import android.util.Log
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
    fun run(sync: Boolean = false): Job {
        return GlobalScope.launch {
            Log.e("isittho", ReferenceHolder.moeOpMode.iOpModeIsActive().toString())

            while (ReferenceHolder.moeOpMode.iOpModeIsActive()) {
                Log.e("working", "work")
//                Log.e("xPid", "work")
                val curInput = input()
                if (endCondition(curInput)) {
                    Log.e("job", "done")
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
