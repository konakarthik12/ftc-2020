package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER
import java.lang.Math.PI

class OdometryConfig(config: MotorConfig,
                     val turnCorrection: Double,
                     val ticsPerRevolution: Double,
                     val odometryDiameter: Double, val negate: Boolean
) {
    var scalar = odometryDiameter * PI / ticsPerRevolution * if(negate) -1 else 1
    var motorConfig = config
}
