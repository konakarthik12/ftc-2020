package org.firstinspires.ftc.teamcode.teleop

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.*

@TeleOp
class ServoTest : OpMode() {
    lateinit var servoA: Servo
    lateinit var servoB: Servo
    lateinit var flicker: Servo
    lateinit var hopper: Servo
    var servoPosition = 0.0
    override fun init(){
        servoA = hardwareMap.servo["servo1"]
        servoB = hardwareMap.servo["servo2"]
        flicker = hardwareMap.servo["flicker"]
        hopper = hardwareMap.servo["hopper"]
        servoA.position = 0.16
        servoB.position = 0.76
    }

    override fun loop() {
//        servoPosition += gamepad1.right_trigger.toDouble() * 0.003
//        servoPosition -= gamepad1.left_trigger.toDouble() * 0.003
//
//        servoA.position = servoPosition
//        servoB.position = 1.0 - servoPosition
        flicker.position += gamepad1.left_stick_y.toDouble() * 0.002

        telemetry.addData("position", servoPosition)
        telemetry.addData("positionA", servoA.position)
        telemetry.addData("positionB", servoB.position)
        telemetry.addData("y", yToggled)
        telemetry.addData("flicker", flicker.getPosition())
        telemetry.addData("hopper",hopper.position)
        telemetry.addData("rightStickY", gamepad1.right_stick_y)
        if (gamepad1.y && !oldY) yToggled = !yToggled
        oldY = gamepad1.y
        if (yToggled) {
            servoA.position = 0.16
            servoB.position = 0.76
          } else{
              servoA.position = 0.7
              servoB.position = 0.16
          }
        hopper.position += gamepad1.right_stick_y.toDouble() * 0.002
        //hopper down 0.3
        //hopper up 0.8
    }
    var oldY = false
    var yToggled = false
}

