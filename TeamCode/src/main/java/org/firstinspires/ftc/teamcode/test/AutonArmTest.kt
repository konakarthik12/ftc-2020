package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp

@Disabled
@TeleOp
class AutonArmTest : MOETeleOp() {
    var rightClawPos = 0.0
    var leftClawPos = 0.0
    var rightArmPos = 0.0
    var leftArmPos = 0.0

    override fun mainLoop() {
        rightClawPos += (gpad1.right.trigger() - gpad1.left.trigger()) * .025
        leftClawPos += (gpad2.right.trigger() - gpad2.left.trigger()) * .025

        leftArmPos += (gpad2.right.stick.y() + gpad2.right.stick.y()) * .025
        rightArmPos += (gpad1.left.stick.y() + gpad2.left.stick.y()) * .025

        robot.autonArms.left.clawServo.setPosition(leftClawPos)
        robot.autonArms.right.clawServo.setPosition(rightClawPos)
        robot.autonArms.left.armServo.setPosition(leftArmPos)
        robot.autonArms.right.armServo.setPosition(rightArmPos)

        telemetry.addData("Right Claw Pos:", rightClawPos)
        telemetry.addData("Left Claw Pos:", leftClawPos)
        telemetry.addData("Right Arm Pos:", rightArmPos)
        telemetry.addData("Left Claw Pos:", leftArmPos)
        telemetry.update()

    }

}