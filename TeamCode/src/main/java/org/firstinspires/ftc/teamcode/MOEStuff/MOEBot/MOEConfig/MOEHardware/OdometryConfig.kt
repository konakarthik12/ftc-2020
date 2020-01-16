package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER
import java.lang.Math.PI

class OdometryConfig(config: MotorConfig,
                     val turnCorrection: Double,
                     val ticsPerRevolution: Double,
                     val odometryDiameter: Double
) : MotorConfig(config.name, config.hub, config.port) {
    var scalar = odometryDiameter * 2 * PI / ticsPerRevolution
}
