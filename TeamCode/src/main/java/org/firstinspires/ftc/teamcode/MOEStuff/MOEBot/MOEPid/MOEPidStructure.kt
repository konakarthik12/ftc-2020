package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot

interface MOEPidStructure<I, O> {
//    var lastValue: T

    var input: () -> I
    var output: ((O) -> Unit)
    var setpoint: () -> I
    var endCondition: (I, I, I) -> Boolean
    fun run(sync: Boolean = true): Job {
        return GlobalScope.launch {
            Log.e("isittho", ReferenceHolder.moeOpMode.iOpModeIsActive().toString())
            reset()
            while (ReferenceHolder.moeOpMode.iOpModeIsActive()) {
//                Log.e("working", "work")
//                Log.e("xPid", "work")
                val curInput = input()
                val curSetPoint = setpoint()
                val dActual = getAbsActualDiff()
                val neededOutput = getOutput(curInput, curSetPoint)

                if (endCondition(curInput, curSetPoint, dActual)) {
                    Log.e("job", "done")
                    break
                }
                if (moeOpMode.iIsStopRequested) break
                output(neededOutput)
            }
            onFinish()
        }.also {
            if (sync) {
                runBlocking { it.join() }
            }
        }
    }

    fun getAbsActualDiff(): I
    fun reset()
    fun onFinish() {
        robot.chassis.stop()
    }

    //    fun getOutput(input: I): O
    fun getOutput(input: I, setpoint: I): O

}
