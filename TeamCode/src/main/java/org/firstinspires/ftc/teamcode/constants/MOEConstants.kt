package org.firstinspires.ftc.teamcode.constants

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MotorConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.ServoConfig
import org.firstinspires.ftc.teamcode.utilities.PurePursuit.MOEPurePursuitOptions
import kotlin.math.PI
import com.qualcomm.robotcore.hardware.DcMotorSimple as Motor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.OdometryWheelConfig as OWC

object
MOEConstants {
    object DriveTrain {
        object Motors {
            object Configs {
                val FrontLeft = MotorConfig("FLDM11", Motor.Direction.FORWARD)
                val FrontRight = MotorConfig("FRDM13", Motor.Direction.REVERSE)
                val BackLeft = MotorConfig("BLDM10", Motor.Direction.FORWARD)
                val BackRight = MotorConfig("BRDM12", Motor.Direction.REVERSE)
            }
        }
    }

    object IntakeSystem {
        object Motors {
            object Configs {
                val LeftIntake = MotorConfig("LHVM20", Motor.Direction.REVERSE)
                val RightIntake = MotorConfig("RHVM21", Motor.Direction.FORWARD)
            }

            const val MaxPower = 0.9
        }


    }

    object OutTakeSystem {
        object Servos {
            object Configs {
                val DispenserServo = ServoConfig("OUTS12")
            }
        }
    }

    object FoundationSystem {
        object Servos {
            object Configs {
                val FoundationServo = ServoConfig("FNDS23")
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

    object Localization {
        const val CAMERA_DISTANCE = 15.0 * Units.METERS_PER_ASTAR
        const val CAMERA_THETA = -10.0  // Angle relative to robot from camera
        const val INITIAL_CAMERA_THETA = -100.0
    }

    object PurePursuit {
        val DefaultOptions = MOEPurePursuitOptions(
                overallMaxVelocity = -1.0,
                spacing = -1.0,
                tolerance = -1.0,
                smoothingA = -1.0,
                smoothingB = -1.0,
                turningConstant = 0.0,
                lookBack = 0,
                lookForward = 0,
                lookAheadDistance = 0.0,
                track_width = 0.0,
                K_V = 0.0,
                K_A = 0.0,
                K_P = 0.0
        )

        val FINISHED_TOLERANCE: Double = 0.5
    }

    object Units {
        const val METER_PER_FEET = 0.3048
        const val FEET_PER_TILE = 2
        const val METERS_PER_TILE = FEET_PER_TILE * METER_PER_FEET
        const val ASTARS_PER_TILE = 48
//        const val METERS_PER_ASTAR = 0.0127
        const val METERS_PER_ASTAR = METERS_PER_TILE / ASTARS_PER_TILE
        const val ASTARS_PER_METER = ASTARS_PER_TILE / METERS_PER_TILE
    }

    object Slam {
    }
}
