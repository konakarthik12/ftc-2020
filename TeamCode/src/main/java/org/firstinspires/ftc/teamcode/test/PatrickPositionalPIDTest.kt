package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOETurnPid
import org.firstinspires.ftc.teamcode.constants.MOEPidConstants
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toNormalAngle
import org.firstinspires.ftc.teamcode.utilities.external.toFixed

@Disabled
@TeleOp(name = "PatrickPositionalPidTest")
class PatrickPositionalPIDTest : MOERegularTest() {
    //    lateinit var xPid: MOEPositionalPid
    //    lateinit var yPid: MOEPositionalPid
    //    lateinit var tPid: MOETurnPid
//    var systemPid = MOEPositionalSystemPid(MOEPidConstants.PositionalPid.DefaultOptions)
    var tPid: MOETurnPid = MOETurnPid(MOEPidConstants.tOptions)


    override fun initOpMode() {
//        telemetry.addData("waiting for slam")
//        telemetry.update()
//        robot.slam.restart()
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

    fun mainLoop() {
//        val moetion = robot.odometry.()
//        val pose = moetion.pose

//        pc.getPose *= MOEConstants.Units.ASTARS_PER_METER
//        pc.getPose *= -1.0
//        pose *= MOEConstants.Units.ASTARS_PER_METER
//        pose *= -1.0
        //        while (gamepad1.a){
        //        }
        //val setPointPoint = Point(systemPid.xPid.setpoint(), systemPid.yPid.setpoint());
//        logData(pose, angle)
//        val rawX = systemPid.xPid.getOutput(pose.x)
//        val rawY = systemPid.yPid.getOutput(pose.y)

        val input = robot.gyro.angle
        val rot = tPid.getOutput(input)

//        val fwd = rawX * sin(Math.toRadians(angle)) + rawY * cos(Math.toRadians(angle))
//        val str = rawX * cos(Math.toRadians(angle)) - rawY * sin(Math.toRadians(angle))
//        telemetry.addData("FWD", fwd.toFixed())
//        telemetry.addData("STR", str.toFixed())
        telemetry.addData("ROT", rot.toFixed())
        logData(input)

        //        telemetry.addData("curPose", pc.getPose.x.toFixed())
        //        telemetry.addData("curAngle", robot.gyro.angle)
        //        telemetry.addData("goal", systemPid.yPid.setpoint.toFixed())
        //        telemetry.addData("error", systemPid.yPid.getError(systemPid.yPid.setpoint, pc.getPose.y).toPrecision())


//        val powers = fromMecanum(fwd, str, rot)
        //        telemetry.addData("powers", powers.toString())
//        val yPid = systemPid.yPid
//        val xPid = systemPid.xPid
//        val multi = 48.0
//        val yPid =
//        if (gamepad1.y && !oldUp) ySet += multi
//        if (gamepad1.a && !oldDown) ySet -= multi
//        if (gamepad1.b && !oldRight) xSet += multi
//        if (gamepad1.x && !oldLeft) xSet -= multi
        if (gamepad1.dpad_left && !oldDpadLeft) {
            tSet -= 90
            tPid.reset()
        }
        if (gamepad1.dpad_right && !oldDpadRight) {
            tSet += 90
            tPid.reset()

        }
        tSet = tSet.toNormalAngle()

        if (gamepad1.right_bumper) {
//            xSet = 0.0
//            ySet = 0.0
            tSet = 0.0
        }

//        yPid.setpoint = { ySet }
//        xPid.setpoint = { xSet }
        tPid.setpoint = { tSet }
//        tPid.setpoint = { tPid.setpoint().coerceIn(0.0..359.0) }

//        oldUp = gamepad1.y
//        oldDown = gamepad1.a
//        oldLeft = gamepad1.x
//        oldRight = gamepad1.b
        oldDpadLeft = gamepad1.dpad_left
        oldDpadRight = gamepad1.dpad_right
//        telemetry.update()

        if (gamepad1.right_trigger > 0.5) {
            robot.chassis.stop()
            return
        }

        robot.chassis.turnPower(rot)
        //        while (gamepad1.a) {
        //            robot.chassis.setVelocity(0.0)
        //            telemetry.addData("waiting for key")
        //            telemetry.update()
        //        }
    }

    private fun logData(angle: Double) {
        //        systemPid.yPid.setpoint += gamepad1.left_stick_y
        //        systemPid.xPid.setpoint += gamepad1.left_stick_x
        //        systemPid.tPid.setpoint += gamepad1.right_stick_x
//        telemetry.addData("egoalY", systemPid.yPid.setpoint())
//        telemetry.addData("curY", pose.y)
//        telemetry.addData("egoalX", systemPid.xPid.setpoint())
//        telemetry.addData("curX", pose.x)
        val setpoint = tPid.setpoint()
        tPid.setOutputLimits(0.5)
        telemetry.addData("goaT", setpoint)
        telemetry.addData("curT", angle)
        telemetry.addData("error", tPid.internalPid.getError(setpoint, angle))
        telemetry.update()

    }


    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply {
            useOdometry = false
        }
    }
}
