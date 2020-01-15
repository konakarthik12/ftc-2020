package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Powers.Companion.fromMecanum
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.Transformation
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOESlamConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.*
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOESlam.MOEPatrickTrans
import org.firstinspires.ftc.teamcode.constants.MOEPidConstants
import org.firstinspires.ftc.teamcode.constants.MOESlamConstants
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toNormalAngle
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import org.firstinspires.ftc.teamcode.utilities.internal.get
import org.firstinspires.ftc.teamcode.utilities.external.toFixed
import kotlin.math.cos
import kotlin.math.sin

@TeleOp(name = "PatrickPositionalPidTest")
class PatrickPositionalPIDTest : MOERegularTest() {
    //    lateinit var xPid: MOEPositionalPid
    //    lateinit var yPid: MOEPositionalPid
    //    lateinit var tPid: MOETurnPid
    var systemPid = MOEPositionalSystemPid(MOEPidConstants.PositionalPid.DefaultOptions)

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["desmos"]
    }

    override fun initOpMode() {
        telemetry.addData("waiting for slam")
        telemetry.update()
        robot.slam.restart()
        //        telemetry.addData("slam", "yeah")
//        robot.slam.config = MOESlamConfig(0.0, 0.0, 0.0)
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
    var ySet = 0.0
    var xSet = 0.0
    var tSet = 0.0
    private val moePatrickTrans = MOEPatrickTrans(MOESlamConstants.DefaultValues)

    fun mainLoop() {
        val cameraTrans = moePatrickTrans.getCameraTrans(Transformation(robot.slam.getRawTrans().pose, robot.gyro.angle))
        val pose1 = moePatrickTrans.getRobotTrans(cameraTrans)
        val pose = pose1.pose
        val angle = robot.gyro.angle + moePatrickTrans.config.robotInitial.degAng
//        pose *= MOEConstants.Units.ASTARS_PER_METER
//        pose *= -1.0
        //        while (gamepad1.a){
        //        }
        //val setPointPoint = Point(systemPid.xPid.setpoint(), systemPid.yPid.setpoint());
        logData(pose, angle)
        val rawX = systemPid.xPid.getOutput(pose.x)
        val rawY = systemPid.yPid.getOutput(pose.y)

        val rot = systemPid.tPid.getOutput(angle)

        val fwd = rawX * sin(Math.toRadians(angle)) + rawY * cos(Math.toRadians(angle))
        val str = rawX * cos(Math.toRadians(angle)) - rawY * sin(Math.toRadians(angle))
        telemetry.addData("FWD", fwd.toFixed())
        telemetry.addData("STR", str.toFixed())
        telemetry.addData("ROT", rot.toFixed())

        //        telemetry.addData("curPose", pose.x.toFixed())
        //        telemetry.addData("curAngle", robot.gyro.angle)
        //        telemetry.addData("goal", systemPid.yPid.setpoint.toFixed())
        //        telemetry.addData("error", systemPid.yPid.getError(systemPid.yPid.setpoint, pose.y).toPrecision())


        val powers = fromMecanum(fwd, str, rot)
        //        telemetry.addData("powers", powers.toString())
        val yPid = systemPid.yPid
        val xPid = systemPid.xPid
        val tPid = systemPid.tPid
        val multi = 48.0
//        val yPid =
        if (gamepad1.y && !oldUp) ySet += multi
        if (gamepad1.a && !oldDown) ySet -= multi
        if (gamepad1.b && !oldRight) xSet += multi
        if (gamepad1.x && !oldLeft) xSet -= multi
        if (gamepad1.dpad_left && !oldDpadLeft) tSet -= 90
        if (gamepad1.dpad_right && !oldDpadRight) tSet += 90
        tSet = tSet.toNormalAngle()

        if (gamepad1.right_bumper) {
            xSet = 0.0
            ySet = 0.0
            tSet = 0.0
        }

        yPid.setpoint = { ySet }
        xPid.setpoint = { xSet }
        tPid.setpoint = { tSet }
//        tPid.setpoint = { tPid.setpoint().coerceIn(0.0..359.0) }

        oldUp = gamepad1.y
        oldDown = gamepad1.a
        oldLeft = gamepad1.x
        oldRight = gamepad1.b
        oldDpadLeft = gamepad1.dpad_left
        oldDpadRight = gamepad1.dpad_right
        telemetry.update()

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

    private fun logData(pose: Point, angle: Double) {
        //        systemPid.yPid.setpoint += gamepad1.left_stick_y
        //        systemPid.xPid.setpoint += gamepad1.left_stick_x
        //        systemPid.tPid.setpoint += gamepad1.right_stick_x
        telemetry.addData("egoalY", systemPid.yPid.setpoint())
        telemetry.addData("curY", pose.y)
        telemetry.addData("egoalX", systemPid.xPid.setpoint())
        telemetry.addData("curX", pose.x)
        telemetry.addData("goaT", systemPid.tPid.setpoint())
        telemetry.addData("curT", angle)
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
//        systemPid.setSetpoints(0.0, 0.0, 0.0)
    }

    override fun getRobotConfig(): MOEBotConfig {
        return super.getRobotConfig().apply { useSlam = true }
    }
}
