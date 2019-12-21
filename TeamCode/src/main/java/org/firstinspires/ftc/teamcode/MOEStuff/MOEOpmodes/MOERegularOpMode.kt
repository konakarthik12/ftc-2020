package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
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
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.telemetry
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.wait

abstract class MOERegularOpMode : LinearOpMode(), MOEFirebase, OpModeInterface {
    val firelog = MOETelemetry(telemetry)
    lateinit var ref: DatabaseReference
    lateinit var robot: MOEBot

    override fun iOpModeIsActive(): Boolean = opModeIsActive()

    override val iTelemetry: Telemetry
        get() = this.telemetry

    override val iHardwareMap: HardwareMap
        get() = this.hardwareMap

    override val iIsStopRequested: Boolean
        get() = this.isStopRequested

    final override fun runOpMode() {
        moeDoubleInternalInit()
        moeInternalInit()
        setRobotRef(robot)
        initOpMode()
        moeInternalPostInit()
        waitForStart()
        resetRobotValues()
        run()
    }


    override fun waitForStart() {
        while (!isStarted) {
            notifyTelemetry()
        }
    }

    private fun resetRobotValues() {
        robot.resetValues()
    }

    private fun moeDoubleInternalInit() {
        setRefs()
        createGamePads()
        addListener()?.let {
            ref = it
        }
        //        ref = addListener()
    }

    private fun createGamePads() {
        //        mainGamepad = MOEGamePad(gamepad1)
    }

    private fun setRefs() {
        ReferenceHolder.setRefs(this)
    }

    //    private fun addListener() {
    //        val customRef = getCustomRef(MOEConfig.config) ?: return
    //        customRef.addValueEventListener(object : MOEEventListener() {
    //            override fun onDataChange(snapshot: DataSnapshot) {
    //                onConfigChanged(snapshot)
    //            }
    //        })
    //        customRef.addChildEventListener(this)
    //        ref = customRef
    //    }

    private fun notifyTelemetry() {
        telemetry.addData("waiting for start")
        telemetry.update()
    }

    open fun moeInternalInit() {

    }

    open fun initOpMode() {

    }

    open fun moeInternalPostInit() {

    }

    abstract fun run()
}