package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.delete
import org.firstinspires.ftc.teamcode.utilities.get
import org.firstinspires.ftc.teamcode.utilities.pushData

@TeleOp(name = "DriveTest")
class DriveTest : MOETeleOp(useSlam = true) {
    val laped = ElapsedTime()
    override fun initOpMode() {
        ref.delete()
        telemetry.addData("testagain")
        robot.odometry.servos.initServosUp()
    }

    override fun loopStuff() {
        telemetry.addData("slam", robot.slam)
        telemetry.update()
        if (laped.seconds() > 0.25) {
            ref.pushData {
                robot.slam.getCameraPose()
            }
            laped.reset()

        }
        val maxPower = 1.0;
        val scaleX = 1;
        val scaleY = 0.8;
        val scaleRot = 0.3;

        var rawX = gamepad1.left_stick_x.toDouble()
        var rawY = (-gamepad1.left_stick_y).toDouble()
        var rot = gamepad1.right_stick_x.toDouble()
        rawX *= scaleX;
        rawY *= scaleY;
        rot *= scaleRot;

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

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref["slamlist"]
    }

}

//private fun DatabaseReference.pushData(function: () -> Point3) {
//
//}




