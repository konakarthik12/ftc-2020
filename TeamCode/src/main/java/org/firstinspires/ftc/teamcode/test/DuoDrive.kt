package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData

@TeleOp(name = "DuoDrive")
class DuoDrive : MOETeleOp() {
    override fun initOpMode() {
        Log.e("stuffe", "stuffe")
        telemetry.addData("testagain")
    }

    override fun loopStuff() {
        val P = (gamepad2.left_trigger - gamepad2.right_trigger).toDouble() * 0.4
        telemetry.addData("Power: ", P)
        telemetry.update()
        robot.harvester.setPower(P)

        val maxPower = 1.0
        val scaleX = 1
        val scaleY = 0.6
        val scaleRot = 0.3

        var rawX = gamepad1.left_stick_x.toDouble()
        var rawY = (-gamepad1.left_stick_y).toDouble()
        var rot = gamepad1.right_stick_x.toDouble()
        rawX *= scaleX * gamepad1.right_trigger;
        rawY *= scaleY * gamepad1.right_trigger;
        rot *= scaleRot * gamepad1.right_trigger;

        var fwd = rawY
        fwd *= fwd * fwd
        var FRP = fwd - rawX - rot
        var FLP = fwd + rawX + rot
        var BRP = fwd + rawX - rot
        var BLP = fwd - rawX + rot

        var max = if (FRP > maxPower) FRP else maxPower
        if (max < FRP) {
            max = FRP
        }
        if (max < BLP) {
            max = BLP
        }
        if (max < BRP) {
            max = BRP
        }
        if (max < FLP) {
            max = FLP
        }

        FLP /= max
        FRP /= max
        BLP /= max
        BRP /= max

        robot.chassis.setPower(FLP, FRP, BLP, BRP)
    }
}
