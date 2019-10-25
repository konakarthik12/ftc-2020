package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.odometry

import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.odometry.OdometryWheelConfig.Direction
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.odometry.OdometryWheelConfig.Orientation
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Odometry.Wheels
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Odometry.Wheels.MAX_VOLTAGE
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Odometry.Wheels.VOLTS_TO_HALF_INCH

import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap

data class OdometryWheelConfig(val name: String, val direction: Direction, val orientation: Orientation) {
    enum class Direction {
        FORWARD, REVERSE;

        fun scalar(): Double {
            return when (this) {
                FORWARD -> 1.0
                REVERSE -> -1.0
            }
        }
    }

    enum class Orientation {
        AXIAL, STRAFE;

        fun circumference(): Double {
            return when (this) {
                AXIAL -> Wheels.Circumference.AXIAL
                STRAFE -> Wheels.Circumference.STRAFE
            }
        }

        fun rotationalScalar(): Double = circumference() / (2 * Math.PI)
    }
}

class MOEdometryWheel(config: OdometryWheelConfig) {
    companion object {
//        const val MIN_VOLTAGE: Double = 0.0
//        const val MAX_VOLTAGE: Double = 5.0
//
//        private const val AXIAL_CIRCUMFERENCE: Double = 0.0
//        private const val AXIAL_ROTATION_SCALAR: Double = AXIAL_CIRCUMFERENCE / (2 * Math.PI)
//
//        private const val STRAFE_CIRCUMFERENCE: Double = 0.0
//        private const val STRAFE_ROTATION_SCALAR: Double = STRAFE_CIRCUMFERENCE / (2 * Math.PI)


    }

    val odometry: AnalogInput = hardwareMap.get(AnalogInput::class.java, config.name)

    private val direction: Direction = config.direction
    private val directionScalar: Double = config.direction.scalar()
    val orientation: Orientation = config.orientation
    val circumference = orientation.circumference()

    private var prevVoltage: Double = 0.0
    private var voltageChange: Double = 0.0

    val voltage: Double
        get() = odometry.voltage * directionScalar

    init {
        prevVoltage = voltage
    }

    fun getDistanceChanged(): Double = voltageChange * VOLTS_TO_HALF_INCH

    fun update() {
        val currentVoltage = voltage

        voltageChange = if (prevVoltage < currentVoltage) {
            val innerRange = currentVoltage - prevVoltage
            val outerRange = MAX_VOLTAGE - innerRange
            if (innerRange < outerRange) innerRange else -outerRange
        } else {
            val innerRange = prevVoltage - currentVoltage
            //TODO: ROHAN, is this right? is MIN_VOLTAGE used
            val outerRange = MAX_VOLTAGE - innerRange
            if (innerRange < outerRange) -innerRange else outerRange
        }

        prevVoltage = currentVoltage
    }
}
