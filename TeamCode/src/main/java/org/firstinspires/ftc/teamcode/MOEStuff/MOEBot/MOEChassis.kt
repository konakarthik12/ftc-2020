package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import org.firstinspires.ftc.teamcode.constants.MOEConstants.DriveTrain.Motors.Configs

class MOEChassis : ListIterator<MOEtor> {


    private var frontLeftMotor = MOEtor(Configs.FrontLeft)
    private var frontRightMotor = MOEtor(Configs.FrontRight)
    private var backLeftMotor = MOEtor(Configs.BackLeft)
    private var backRightMotor = MOEtor(Configs.BackRight)
    private val motors = listOf(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor)

    fun setPower(P: Double) = setPower(P, P)
    fun setVelocity(V: Double) = setVelocity(V, V)

    fun setPower(LP: Double, RP: Double) = setPower(LP, RP, LP, RP)
    fun setVelocity(LV: Double, RV: Double) = setVelocity(LV, RV, LV, RV)

    fun setPower(FLP: Double, FRP: Double, BLP: Double, BRP: Double) {
        frontLeftMotor.setPower(FLP)
        frontRightMotor.setPower(FRP)
        backLeftMotor.setPower(BLP)
        backRightMotor.setPower(BRP)
    }

    fun setVelocity(FLV: Double, FRV: Double, BLV: Double, BRV: Double) {
        frontLeftMotor.setPower(FLV)
        frontRightMotor.setPower(FRV)
        backLeftMotor.setPower(BLV)
        backRightMotor.setPower(BRV)
    }

    fun turnPower(power: Double) = setPower(power, -power)

    fun turnRightPower(power: Double) = turnPower(power)
    fun turnRightLeft(power: Double) = turnPower(-power)

    fun stop() {
        setPower(0.0)
    }

    private val listIterator = motors.listIterator()

    override fun hasNext(): Boolean = listIterator.hasNext()

    override fun hasPrevious(): Boolean = listIterator.hasPrevious()

    override fun next(): MOEtor = listIterator.next()

    override fun nextIndex(): Int = listIterator.nextIndex()

    override fun previous(): MOEtor = listIterator.previous()
    override fun previousIndex(): Int = listIterator.previousIndex()
}
