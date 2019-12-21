package org.firstinspires.ftc.teamcode.constants

import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MotorConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.ServoConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.AutonConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.reflectAutonConfig
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Rectangle
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.PurePursuit.MOEPurePursuitOptions
import kotlin.math.PI
import com.qualcomm.robotcore.hardware.DcMotorSimple as Motor
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.OdometryWheelConfig as OWC

object
MOEConstants {
    object DriveTrain {
        object Motors {
            object Configs {
                val FrontLeft = MotorConfig("FLDM12", Motor.Direction.FORWARD)
                val FrontRight = MotorConfig("FRDM10", Motor.Direction.REVERSE)
                val BackLeft = MotorConfig("BLDM13", Motor.Direction.FORWARD)
                val BackRight = MotorConfig("BRDM11", Motor.Direction.REVERSE)
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
                val FoundationServo1 = ServoConfig("FDRS23", 0.0, 1.0, Servo.Direction.FORWARD)
                val FoundationServo2 = ServoConfig("FDLS20", 0.0, 1.0, Servo.Direction.REVERSE)
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

    object SLAM {
        const val CAMERA_DISTANCE = 11.0 * Units.METERS_PER_ASTAR
        //        const val CAMERA_THETA = -10.0  // Angle relative to robot from camera
        const val INITIAL_CAMERA_THETA = -82.0
    }

    object PurePursuit {
        val DefaultOptions = MOEPurePursuitOptions(
                overallMaxVelocity = 1.0,
                spacing = 3.0,
                tolerance = 0.0005,
                smoothingA = 1 - 0.7,
                smoothingB = 0.7,
                turningConstant = 0.1,
                lookBack = 4,
                lookForward = 4,
                lookAheadDistance = 20.0,
                track_width = 28.0,
                K_V = 0.0,
                K_A = 0.0,
                K_P = 0.0
        )

        const val FINISHED_TOLERANCE: Double = 0.5
    }

    object Autonomous {
        val Left = AutonConfig(
                skystoneCropRect = Rectangle(0, 0, 0, 0),
                robotToFieldOffset = 90.0,
                positionOffsets = Point(0.0, 0.0), // Point(15.0, 96.0),
                topSkystonePosition = Point(96.0 + 4.0, 96.0 - 8.0),
                afterSkystonePosition = Point(48.0, 96.0),
                dumpSkystonePosition = Point(48.0, 192.0),
                parkPosition = Point(63.0, 144.0)
        )
        val Right = reflectAutonConfig(Left)
    }

    object Units {
        const val METER_PER_FEET = 0.3048
        const val FEET_PER_TILE = 2
        const val METERS_PER_TILE = FEET_PER_TILE * METER_PER_FEET
        const val ASTARS_PER_TILE = 48
        //        const val METERS_PER_ASTAR = 0.0127
        const val METERS_PER_ASTAR = METERS_PER_TILE / ASTARS_PER_TILE
        const val ASTARS_PER_METER = ASTARS_PER_TILE / METERS_PER_TILE

        const val SKYSTONE_WIDTH = 4.0 * 2.0
        const val SKYSTONE_LENGTH = 8.0 * 2.0
        const val NUM_QUARRY_STONES = 6
        const val FIELD_SIZE = 288.0

        const val TICKS_PER_ASTAR = 19.8333333
        const val ASTARS_PER_TICK = 1 / TICKS_PER_ASTAR
    }

    object Vuforia {
        const val LICENSE_KEY = "AVmJqSr/////AAABmeGcV96ftk3KgMDBC6fRLL4Qn/iAktqBNx29ewqo4NPP1TWeg6cpqfRV3dQ3vtDc4LxnL" +
                "DckSBYdv9v3b1pe2Rq1v+stEVsKhckekqcdpbffWqz+QAyFJF7Mg9q/vOVBXIjvH7CPFVVKiM/M+J3vFw87SFxJKQlZuOM0WGi0hM" +
                "Jf8CE21ZJKh3h9tCg+/dqEux2FmB7XpezHFFeIqE8EK/3skt8Gjui+ywRSmgyzr+C3GswiIWsUn3YYCS6udgB8O6ntF5RZyrq4dQJ" +
                "xrdV1Mh1P7dlpGgyml+yiBAKDgoHZPiHKBx1TIY0Gg9QBebnuHdMvEOhK9oOJqtlR7XBO+fRJrXSCBY+9zBHRpZ6zSE0P"
    }

    object Gamepad {
        const val ON_THRESHOLD = 0.95
    }

    object GyroConfig {
        val parameters = BNO055IMU.Parameters().apply {
            angleUnit = BNO055IMU.AngleUnit.DEGREES
            mode = BNO055IMU.SensorMode.IMU
            accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC
            loggingEnabled = false
        }
    }
}
