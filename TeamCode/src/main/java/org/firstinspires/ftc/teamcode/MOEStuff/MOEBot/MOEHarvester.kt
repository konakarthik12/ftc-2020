package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.IntakeSystem.Motors.Configs

class MOEHarvester {
    private var leftIntakeMotor = MOEtor(Configs.LeftIntake)
    private var rightIntakeMotor = MOEtor(Configs.RightIntake)

    fun setPower(P: Double) = setPower(P, P)
    fun setVelocity(V: Double) = setVelocity(V, V)

    fun setPower(LP: Double, RP: Double) {
        leftIntakeMotor.setPower(LP)
        rightIntakeMotor.setPower(RP)
    }

    fun setVelocity(LV: Double, RV: Double) {
        leftIntakeMotor.setPower(LV)
        rightIntakeMotor.setPower(RV)
    }
}
