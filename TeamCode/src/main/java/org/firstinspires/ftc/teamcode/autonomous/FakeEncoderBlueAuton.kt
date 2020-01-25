package org.firstinspires.ftc.teamcode.autonomous

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEAuton
import org.firstinspires.ftc.teamcode.utilities.internal.waitSeconds

@Autonomous
class FakeEncoderBlueAuton : MOEAuton() {
    override fun run() {
        robot.foundation.moveUp()
        robot.chassis.encoders.moveForwardAStars(5.0, 0.6)
        robot.chassis.encoders.moveLeftAstars(5.0, 0.6)
        robot.chassis.encoders.moveForwardAStars(5.0, 0.6)
        robot.foundation.moveDown()
        robot.chassis.encoders.moveBackwardAStars(9.0, 0.6)
    }


}