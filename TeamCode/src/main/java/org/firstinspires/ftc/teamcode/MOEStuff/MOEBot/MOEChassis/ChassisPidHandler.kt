package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPositionalSystemPid
import org.firstinspires.ftc.teamcode.constants.MOEPidConstants
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.robot

class ChassisPidHandler {
    val pid = MOEPositionalSystemPid(MOEPidConstants.PositionalPid.DefaultOptions)

    init {
        pid.input= {
            robot.slam.getTrans()
        }
        pid.output = { powers ->
            robot.chassis.setPower(powers)
        }
    }

    fun moveTo(x: Double, y: Double) {
        pid.reset()
        pid.setSetpoints(x, y, robot.gyro.angle)
        pid.run()
    }

}
