package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcontroller.moeglobal.firebase.MOEConfig
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEFirebase.MOEEventListener
import org.firstinspires.ftc.teamcode.MOEStuff.MOEFirebase.MOEFirebase
//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOETelemetry
import org.firstinspires.ftc.teamcode.constants.OpModeInterface
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.setRobotRef
import org.firstinspires.ftc.teamcode.utilities.addData

abstract class MOELoopedOpMode() : OpMode(), MOEFirebase, OpModeInterface {
    lateinit var ref: DatabaseReference
    val firelog = MOETelemetry(telemetry)
    lateinit var robot: MOEBot
    var opModeIsActive: Boolean = false



    override fun iOpModeIsActive(): Boolean = opModeIsActive

    override val iTelemetry: Telemetry
        get() = telemetry

    override val iHardwareMap: HardwareMap
        get() = this.hardwareMap

    override val iIsStopRequested: Boolean
        get() = !this.opModeIsActive

    //    override fun iOpModeIsActive(): Boolean =

    override fun init() {
        opModeIsActive = false
        moeDoubleInternalInit()
        moeInternalInit()
        setRobotRef(robot)
        initOpMode()
        moeInternalPostInit()
        resetRobotValues()
        notifyInitFinished()
    }

    override fun loop() {
        opModeIsActive = true
        internalLoop()
    }

    open fun internalLoop() {

    }

    open fun moeInternalPostInit() {

    }

    private fun resetRobotValues() {
        robot.resetValues()
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

    open fun moeInternalInit() {

    }

    open fun initOpMode() {

    }
}
