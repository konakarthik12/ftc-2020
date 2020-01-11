package org.firstinspires.ftc.teamcode.constants

import com.qualcomm.hardware.bosch.BNO055IMU
import org.firstinspires.ftc.teamcode.utilities.external.PurePursuit.MOEPurePursuitOptions

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry.OdometryWheelConfig as OWC

object MOEConstants {

    //    object Odometry {
    //        object Servos {
    //            object Configs {
    ////                val Left = ServoConfig("LODS10", 0.0, 0.45, Servo.Direction.REVERSE)
    ////                val Right = ServoConfig("RODS11", 0.55, 1.0, Servo.Direction.FORWARD)
    //            }
    //        }
    //
    //        object Wheels {
    //            object Configs {
    //                val axialLeft = OWC("LOAA12", OWC.Direction.FORWARD, OWC.Orientation.AXIAL)
    //                val axialRight = OWC("ROAA10", OWC.Direction.FORWARD, OWC.Orientation.AXIAL)
    //                val strafeRight = OWC("ROSA11", OWC.Direction.FORWARD, OWC.Orientation.STRAFE)
    //            }
    //
    //            object Circumference {
    //                const val AXIAL = 0.0
    //                const val STRAFE = 0.0
    //            }
    //
    //            const val MIN_VOLTAGE = 0.0
    //            const val MAX_VOLTAGE = 5.0
    //            val WHEEL_CIRCUMFERENCE = 2 * PI * 2  // 2PI * (half inch conversion factor)
    //            val VOLTS_TO_HALF_INCH: Double = WHEEL_CIRCUMFERENCE / 5
    //        }
    //    }

    object SLAM {
        const val CAMERA_DISTANCE = 18.02775637732// astars
        //        const val CAMERA_THETA = -10.0  // Angle relative to robot from camera
        const val INITIAL_CAMERA_THETA = -86.820169880136
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
