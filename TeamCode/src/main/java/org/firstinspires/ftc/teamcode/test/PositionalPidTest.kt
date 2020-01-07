package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers.Companion.fromMecanum
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.*
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.constants.MOEConstants
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import org.firstinspires.ftc.teamcode.utilities.internal.get
import org.firstinspires.ftc.teamcode.utilities.external.toPrecision
import kotlin.math.cos
import kotlin.math.sin

@TeleOp(name = "APositionalPidTest")
class PositionalPidTest : MOERegularTest() {
    //    lateinit var xPid: MOEPositionalPid
    //    lateinit var yPid: MOEPositionalPid
    //    lateinit var tPid: MOETurnPid
    lateinit var systemPid: MOEPositionalSystemPid

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["desmos"]
    }

    override fun initOpMode() {
        telemetry.addData("waiting for slam")
        telemetry.update()
        robot.slam.restart()
        //        telemetry.addData("slam", "yeah")
        robot.slam.config = MOESlamConfig(0.0, 0.0, 0.0)
    }

    override fun run() {
        while (opModeIsActive()) {
            mainLoop()
        }
    }

    var oldDown = false
    private var oldUp = false
    private var oldLeft = false
    private var oldRight = false
    private var oldDpadLeft = false
    private var oldDpadRight = false
    fun mainLoop() {
        val pose = robot.slam.getCameraPose()
        pose *= MOEConstants.Units.ASTARS_PER_METER
        //        while (gamepad1.a){
        //        }
        val setPointPoint = Point(systemPid.xPid.setpoint, systemPid.yPid.setpoint);
        logData(pose, setPointPoint)
        val rawX = systemPid.xPid.getOutput(pose.x)
        val rawY = systemPid.yPid.getOutput(pose.y)
        val angle = robot.gyro.angle
        val rot = systemPid.tPid.getOutput(angle)

        val fwd = rawX * sin(Math.toRadians(angle)) + rawY * cos(Math.toRadians(angle))
        val str = rawX * cos(Math.toRadians(angle)) - rawY * sin(Math.toRadians(angle))
        telemetry.addData("FWD", fwd.toPrecision())
        telemetry.addData("STR", str.toPrecision())
        telemetry.addData("ROT", rot.toPrecision())

        //        telemetry.addData("curPose", pose.x.toPrecision())
        //        telemetry.addData("curAngle", robot.gyro.angle)
        //        telemetry.addData("goal", systemPid.yPid.setpoint.toPrecision())
        //        telemetry.addData("error", systemPid.yPid.getError(systemPid.yPid.setpoint, pose.y).toPrecision())


        val powers = fromMecanum(fwd, str, rot)
        //        telemetry.addData("powers", powers.toString())
        //        telemetry.update()
        val yPid = systemPid.yPid
        val xPid = systemPid.xPid
        val tPid = systemPid.tPid
        val multi = 48.0
        if (gamepad1.y && !oldUp) yPid.setpoint += multi
        if (gamepad1.a && !oldDown) yPid.setpoint -= multi
        if (gamepad1.b && !oldRight) xPid.setpoint += multi
        if (gamepad1.x && !oldLeft) xPid.setpoint -= multi
        if (gamepad1.dpad_left && !oldDpadLeft) tPid.setpoint -= 90
        if (gamepad1.dpad_right && !oldDpadRight) tPid.setpoint += 90
        tPid.setpoint = tPid.setpoint.coerceIn(0.0..359.0)

        oldUp = gamepad1.y
        oldDown = gamepad1.a
        oldLeft = gamepad1.x
        oldRight = gamepad1.b
        oldDpadLeft = gamepad1.dpad_left
        oldDpadRight = gamepad1.dpad_right

        if (gamepad1.right_trigger > 0.5) {
            robot.chassis.setPower(0.0)
            return
        }

        robot.chassis.setPower(powers)
        //        while (gamepad1.a) {
        //            robot.chassis.setVelocity(0.0)
        //            telemetry.addData("waiting for key")
        //            telemetry.update()
        //        }
    }

    private fun logData(pose: Point, pointPoint: Point) {
        //        systemPid.yPid.setpoint += gamepad1.left_stick_y
        //        systemPid.xPid.setpoint += gamepad1.left_stick_x
        //        systemPid.tPid.setpoint += gamepad1.right_stick_x
        telemetry.addData("goalY", systemPid.yPid.setpoint)
        telemetry.addData("curY", pose.y)
        telemetry.addData("goalX", systemPid.xPid.setpoint)
        telemetry.addData("curX", pose.x)
        telemetry.addData("goaT", 0.0)
        telemetry.addData("curT", robot.gyro.angle)
        telemetry.update()

    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val xOptions = dataSnapshot["xPID"].getValue(MOEPidOptions::class.java)!!
        val yOptions = dataSnapshot["yPID"].getValue(MOEPidOptions::class.java)!!
        val tOptions = dataSnapshot["tPID"].getValue(MOEPidOptions::class.java)!!
        Log.e("focus", yOptions.toString())
        systemPid = MOEPositionalSystemPid(xOptions, yOptions, tOptions)
        systemPid.xPid.setOutputLimits(1.0)
        systemPid.yPid.setOutputLimits(1.0)
        systemPid.tPid.setOutputLimits(1.0)
        //(x,y,t)
        systemPid.setSetpoints(0.0, 0.0, 0.0)
    }

    override fun getRobotConfig(): MOEBotConfig {
        return super.getRobotConfig().apply { useSlam = true }
    }
}
