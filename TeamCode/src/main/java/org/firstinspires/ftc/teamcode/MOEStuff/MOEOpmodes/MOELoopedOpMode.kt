package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBotConstants
import org.firstinspires.ftc.teamcode.MOEStuff.MOEFirebase.MOEFirebase
//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad
import org.firstinspires.ftc.teamcode.constants.OpModeInterface
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.setRobotRef
import org.firstinspires.ftc.teamcode.utilities.internal.addData

abstract class MOELoopedOpMode() : OpMode(), MOEFirebase, OpModeInterface, MOEBotConstants {
    lateinit var ref: DatabaseReference
    lateinit var robot: MOEBot
    var opModeIsActive: Boolean = false

    @Volatile
    var isStopRequested = false


    override fun iOpModeIsActive(): Boolean = opModeIsActive

    override val iTelemetry: Telemetry
        get() = telemetry

    override val iHardwareMap: HardwareMap
        get() = this.hardwareMap

    override val iIsStopRequested: Boolean
        get() = isStopRequested

    //    override fun iOpModeIsActive(): Boolean =

    final override fun init() {

        opModeIsActive = false
        moeDoubleInternalInit()
        initRobot()
        moeInternalInit()
        setRobotRef(robot)
        initOpMode()
        moeInternalPostInit()
        offsetRobotValues()
        notifyInitFinished()
    }

    private fun initRobot() {
        robot = createRobot()
    }


    override fun loop() {
//        val currentTimeMillis = System.currentTimeMillis()

        opModeIsActive = true
        internalLoop()
//        telemetry.addData(System.currentTimeMillis() - currentTimeMillis)

    }

    open fun internalLoop() {

    }

    open fun moeInternalPostInit() {

    }


    private fun moeDoubleInternalInit() {
        setRefs()
        addListener()?.let {
            ref = it
        }
    }


    private fun setRefs() {
        ReferenceHolder.setRefs(this)
    }


    private fun notifyInitFinished() {
        telemetry.addData("waiting for start")
        telemetry.update()
    }

    private fun offsetRobotValues() {
        robot.offsetValues(this)
    }

    open fun moeInternalInit() {}

    open fun initOpMode() {}

    override fun stop() {
        isStopRequested = true
        robot.stop()
//        Log.e("stopped", "stop")
    }
}
