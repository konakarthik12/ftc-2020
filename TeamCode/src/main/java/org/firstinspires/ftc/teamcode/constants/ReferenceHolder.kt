package org.firstinspires.ftc.teamcode.constants

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEBot
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEOpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOERegularOpMode


// The 'i' prefix is used to avoid naming conflicts.
interface OpModeInterface {
    fun iOpModeIsActive(): Boolean

    val iIsStopRequested: Boolean
    val iTelemetry: Telemetry
    val iHardwareMap: HardwareMap
}


class ReferenceHolder {
    companion object {
        lateinit var moeOpMode: OpModeInterface
        lateinit var telemetry: Telemetry
        lateinit var hardwareMap: HardwareMap
        lateinit var robot: MOEBot

        fun setRefs(opMode: OpModeInterface) {
            moeOpMode = opMode
            telemetry = opMode.iTelemetry
            hardwareMap = opMode.iHardwareMap
        }

        fun setRobotRef(robot: MOEBot) {
            this.robot = robot
        }
    }
}
