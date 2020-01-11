package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

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
            robot.chassis.setPower(powers)
        }
    }

    fun moveTo(goal: Transformation) {
        pid.reset()
        pid.setpoint = { goal }
        pid.run()
    }

    fun moveTo(x: Double, y: Double, angle: Double = robot.gyro.angle) = moveTo(Transformation(x, y, angle))

}
