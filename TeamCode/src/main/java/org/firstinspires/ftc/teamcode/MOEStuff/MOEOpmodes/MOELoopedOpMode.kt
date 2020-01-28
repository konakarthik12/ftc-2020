package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBotConstantsImpl
import org.firstinspires.ftc.teamcode.MOEStuff.MOEFirebase.MOEFirebase
//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad
import org.firstinspires.ftc.teamcode.constants.OpModeInterface
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.setRobotRef
import org.firstinspires.ftc.teamcode.utilities.internal.addData

abstract class MOELoopedOpMode() : OpMode(), MOEFirebase, OpModeInterface, MOEBotConstantsImpl {
    lateinit var ref: DatabaseReference
    lateinit var robot: MOEBot
    var opModeIsActive: Boolean = false

    @Volatile
    var isStopRequested = false

    override fun iRequestOpModeStop() = requestOpModeStop()

    override fun iOpModeIsActive(): Boolean = opModeIsActive && !iIsStopRequested

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

    override fun internalPostInitLoop() {
        super.internalPostInitLoop()
        afterInit()
    }

    open fun afterInit() {
        telemetry.update()
    }

    override fun loop() {
        val currTime = System.nanoTime()
        opModeIsActive = true
        internalLoop()
        telemetry.addData(System.nanoTime() - currTime)

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
