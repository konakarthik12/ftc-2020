package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import kotlinx.coroutines.Job
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPositionalSystemPid
import org.firstinspires.ftc.teamcode.constants.MOEPidConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.MOEtion

class PidChassisHandler {
    private val pid = MOEPositionalSystemPid(MOEPidConstants.PositionalPid.DefaultOptions)

    init {
        pid.input = {
            //   robot.slam.transformation
            robot.odometry.astarMoetion()
//            MOEtion(robot.odometry.pose, robot.gyro.angle)
        }

        pid.output = { powers ->
            //            if(!moeOpMode)
//            Log.e("pow", powers.toString())
            robot.chassis.setPower(powers)
        }
        //TODO: weak rn
        pid.pids.forEach { it.setOutputLimits(0.5) }
    }

    fun moveTo(goal: MOEtion): Job {
        pid.reset()
        pid.setpoint = { goal }
        return pid.run()
    }

    fun moveTo(x: Double, y: Double, angle: Double = robot.gyro.angle) = moveTo(MOEtion(x, y, angle))

}
