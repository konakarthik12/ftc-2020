package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import android.util.Log
import kotlinx.coroutines.Job
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPositionalSystemPid
import org.firstinspires.ftc.teamcode.constants.MOEPidConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot

class ChassisPidHandler {
    private val pid = MOEPositionalSystemPid(MOEPidConstants.PositionalPid.DefaultOptions)

    init {
        pid.input = {
            robot.slam.transformation
        }
        pid.output = { powers ->
            Log.e("pow", powers.toString())
            robot.chassis.setPower(powers)
        }
    }

    fun moveTo(goal: Transformation): Job {
        pid.reset()
        pid.setpoint = { goal }
        return pid.run()
    }

    fun moveTo(x: Double, y: Double, angle: Double = robot.gyro.angle) = moveTo(Transformation(x, y, angle))
    fun turn(deg: Double) = moveTo(robot.slam.transformation.apply { degAng += deg })
}
