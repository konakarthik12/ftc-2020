package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import org.firstinspires.ftc.teamcode.utilities.internal.initOtherOpMode
import org.firstinspires.ftc.teamcode.utilities.internal.wait

@Autonomous
class TransitionTest : MOERegularTest() {

    override fun run() {
        telemetry.addData("wait 5 seconds")
        telemetry.update()
        wait(5000)
        initOtherOpMode(CompTeleOp::class)
        telemetry.addData("wait another 5 seconds")
        telemetry.update()
        wait(5000)

    }

}