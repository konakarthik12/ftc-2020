package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid

open class MOEPidSystem(vararg val pids: MOEFancyPid) {
    fun setSetpoints(goal: Double) {
        pids.forEach { it.setpoint = goal }
    }

    fun setOutputLimits(limit: Double) {
        pids.forEach { it.setOutputLimits(limit) }
    }

    fun setOutputLimits(min: Double, max: Double) {
        pids.forEach { it.setOutputLimits(min, max) }
    }

    fun getOutputs(): List<Double> {
        return pids.map { it.getOutput(it.input()) }
    }

}