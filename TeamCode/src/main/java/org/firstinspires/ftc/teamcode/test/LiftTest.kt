package org.firstinspires.ftc.teamcode.test

//package org.firstinspires.ftc.teamcode.test
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp
//import com.qualcomm.robotcore.hardware.DcMotor
//import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
//
//@TeleOp(name = "LiftTest")
//class LiftTest : CompTeleOp() {
//    override fun initOpMode() {
//        super.initOpMode()
//        gpad2.y.onKeyDown {
//            robot.lift.resetEncoders()
//        }
//        robot.lift.setRunToPosition()
//        robot.lift.motors.forEach {
//            it.mMotor.targetPositionTolerance = 25
//        }
//    }
//
//    var target = 0.0
//    var oldTime = System.currentTimeMillis()
//    override fun lift() {
//        val right = gamepad2.right_trigger.toDouble()
////        val multi = if (target > 0) 3.0 else 0.0
//        val left = gamepad2.left_trigger.toDouble() * multi
//        robot.lift.motors.forEach {
//            val error = it.error
//            if (error > 0) {
//                robot.lift.setPower(1.0)
//            } else {
//                robot.lift.setPower(-1.0)
//
//            }
//        }
//        target += ((right - left) * 25)
//        target = target.coerceAtLeast(0.0)
////        telemetry.addData("delta", 25)
//        robot.lift.setPosition(target.toInt())
////        robot.lift.setPower(1.0)
////        oldTime = System.currentTimeMillis()
//    }
//
//    override fun log() {
//        telemetry.addData("lift", robot.lift.getPositions())
//        telemetry.addData("target", target)
//        telemetry.addData("power", robot.lift.getPowers())
//        //        super.log()
//    }
//
//}
