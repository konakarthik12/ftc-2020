package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.teleop.DuoDrive
import org.firstinspires.ftc.teamcode.utilities.addData
@TeleOp(name = "BetterDriveTest")
class BetterDriveTest : DuoDrive() {
    override fun mainLoop() {
        super.mainLoop()
        telemetry.addData(robot.chassis.motors.joinToString { it.getVelocity().toString() })
    telemetry.update()
    }
}