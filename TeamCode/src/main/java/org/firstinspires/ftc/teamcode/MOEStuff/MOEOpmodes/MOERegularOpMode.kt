package org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBotConstantsImpl
import org.firstinspires.ftc.teamcode.MOEStuff.MOEFirebase.MOEFirebase
import org.firstinspires.ftc.teamcode.constants.OpModeInterface
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.setRobotRef
import org.firstinspires.ftc.teamcode.utilities.internal.addData

abstract class MOERegularOpMode : LinearOpMode(), MOEFirebase, OpModeInterface, MOEBotConstantsImpl {
    lateinit var dataRef: DatabaseReference
    lateinit var robot: MOEBot
    override fun iOpModeIsActive(): Boolean = opModeIsActive()
    override fun iRequestOpModeStop() = requestOpModeStop()
    val globalTimer = ElapsedTime(ElapsedTime.Resolution.MILLISECONDS)

    override val iIsStopRequested: Boolean
        get() = this.isStopRequested

    final override fun runOpMode() {
        moeDoubleInternalInit()
        initRobot()
        moeInternalInit()
        setRobotRef(robot)
        initOpMode()
        moeInternalPostInit()
        waitForStart()
        globalTimer.reset()
        offsetRobotValues()
        run()
        postRun()
    }

    /** milliseconds*/
    fun getActiveTime(): Double {
        return globalTimer.time()
    }

    open fun postRun() {

    }

    private fun initRobot() {
        robot = createRobot()
    }

    fun waitForStop() {
        while (!isStopRequested) {
            notifyTelemetry("waiting for stop")
        }
    }

    override fun waitForStart() {
        while (!isStarted && !isStopRequested) {
            notifyTelemetry("waiting for start")
        }
    }

    private fun moeDoubleInternalInit() {
        ReferenceHolder.setRefs(this)
        addListener()?.let {
            dataRef = it
        }
        //        ref = addListener()
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

    private fun notifyTelemetry(message: String) {
        telemetry.addData(message)
        telemetry.update()
    }

    open fun moeInternalInit() {

    }

    open fun initOpMode() {

    }

    open fun moeInternalPostInit() {

    }


    private fun offsetRobotValues() {
        robot.offsetValues(this)
    }

    abstract fun run()
}