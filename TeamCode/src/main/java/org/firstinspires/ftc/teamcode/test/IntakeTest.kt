package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp

@TeleOp(name = "LiftTest")
class LiftTest : CompTeleOp() {
    override fun initOpMode() {
        super.initOpMode()
        gpad2.y.onKeyDown {
            robot.lift.resetEncoders()
        }
        robot.lift.motors.forEach { it.mMotor.mode = DcMotor.RunMode.RUN_TO_POSITION }
    }

    var target = 0.0
    override fun lift() {
        target += (gpad2.trigger_diff)
        robot.lift.setPosition(target.toInt())
    }

    override fun log() {
        telemetry.addData("lift", robot.lift.getPositions())
        //        super.log()
    }

}
