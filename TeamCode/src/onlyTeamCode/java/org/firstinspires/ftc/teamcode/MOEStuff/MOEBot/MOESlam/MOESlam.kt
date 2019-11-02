package org.firstinspires.ftc.teamcode.MOEStuff.MOESlam

import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamT265Handler
import java.util.*

class MOESlam {
    lateinit var handler: SlamT265Handler

    init {
        checkConnection()
    }

    private fun checkConnection() {
        val t265Handler = SlamHandler.t265Handler
        handler = t265Handler
        handler.killStream()
        handler.startStream()
    }

    fun getPose() = handler.curPose
    override fun toString(): String {

        return Arrays.toString(getPose())
    }
}