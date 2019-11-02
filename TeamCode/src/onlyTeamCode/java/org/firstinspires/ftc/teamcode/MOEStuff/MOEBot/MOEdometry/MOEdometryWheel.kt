package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import com.qualcomm.robotcore.hardware.AnalogInput
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.OdometryWheelConfig.Orientation
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Odometry.Wheels
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Odometry.Wheels.MAX_VOLTAGE
import org.firstinspires.ftc.teamcode.constants.MOEConstants.Odometry.Wheels.VOLTS_TO_HALF_INCH
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap
import kotlin.math.abs

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
    val odometry: AnalogInput = hardwareMap.get(AnalogInput::class.java, config.name)

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

        val innerRange = abs(currentVoltage - prevVoltage)
        val outerRange = MAX_VOLTAGE - innerRange

        voltageChange = if (prevVoltage < currentVoltage) {
            if (innerRange < outerRange) innerRange else -outerRange
        } else {
            if (innerRange < outerRange) -innerRange else outerRange
        }

        prevVoltage = currentVoltage
    }
}
