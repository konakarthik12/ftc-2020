package org.firstinspires.ftc.teamcode.teleop

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp

@TeleOp
class LiftTeleOp : MOETeleOp() {

    override fun mainLoop() {
        var left = gpad1.left.trigger()
        var right = gpad1.right.trigger()
        if (gpad1.left.bumper.isPressed) left--
        if (gpad1.right.bumper.isPressed) right--
        robot.lift.leftMotor.setPower(left)
        robot.lift.rightMotor.setPower(right)
//        telemetry.addData("left", robot.lift.leftMotor.distanceTraveled)
//        telemetry.addData("right", robot.lift.rightMotor.distanceTraveled)
        telemetry.update()
    }


}