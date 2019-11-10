package org.firstinspires.ftc.teamcode.constants

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.robotcore.internal.opengl.models.Geometry
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MotorConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.ServoConfig
import org.firstinspires.ftc.teamcode.utilities.Distance
import org.firstinspires.ftc.teamcode.utilities.feet
import kotlin.math.PI
import com.qualcomm.robotcore.hardware.DcMotorSimple as Motor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.OdometryWheelConfig as OWC

object MOEConstants {
    object DriveTrain {
        object Motors {
            object Configs {
                val FrontLeft = MotorConfig("FLDM11")
                val FrontRight = MotorConfig("FRDM13", Motor.Direction.REVERSE)
                val BackLeft = MotorConfig("BLDM10")
                val BackRight = MotorConfig("BRDM12", Motor.Direction.REVERSE)
            }
        }
    }

    object Odometry {
        object Servos {
            object Configs {
                val Left = ServoConfig("LODS10", 0.0, 0.45, Servo.Direction.REVERSE)
                val Right = ServoConfig("RODS11", 0.55, 1.0, Servo.Direction.FORWARD)
            }
        }

        object Wheels {
            object Configs {
                val axialLeft = OWC("LOAA12", OWC.Direction.FORWARD, OWC.Orientation.AXIAL)
                val axialRight = OWC("ROAA10", OWC.Direction.FORWARD, OWC.Orientation.AXIAL)
                val strafeRight = OWC("ROSA11", OWC.Direction.FORWARD, OWC.Orientation.STRAFE)
            }

            object Circumference {
                const val AXIAL = 0.0 //TODO: Calculate.
                const val STRAFE = 0.0  //TODO: Calculate.
            }

            const val MIN_VOLTAGE = 0.0
            const val MAX_VOLTAGE = 5.0
            const val WHEEL_CIRCUMFERENCE = 2 * PI * 2  // 2PI * (half inch conversion factor)
            const val VOLTS_TO_HALF_INCH: Double = WHEEL_CIRCUMFERENCE / 5
        }
    }

    object PurePursuit {
        const val K_V = 0.0
        const val K_A = 0.0
        const val K_P = 0.0
    }

    object Units {
        const val FEET_PER_METER = 0.3048
        const val SLAMS_PER_TILE = 2 * FEET_PER_METER
        const val ASTARS_PER_TILE = 48
        const val SLAMS_PER_ASTAR = SLAMS_PER_TILE / ASTARS_PER_TILE
        const val ASTAR_PER_SLAM = ASTARS_PER_TILE / SLAMS_PER_TILE
    }

    object Slam {


    }
}
