package org.firstinspires.ftc.teamcode.constants

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.*
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.DriveTrain.Motors.Configs.BackRight
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.IntakeSystem.Motors.Configs.RightIntake
import kotlin.math.min
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction as MotorDirection
import com.qualcomm.robotcore.hardware.Servo.Direction as ServoDirection

object MOEHardwareConstants {

    object DriveTrain {
        object Motors {
            object Configs {
                val FrontLeft = MotorConfig("FLD", 1, 2, MotorDirection.FORWARD)
                val FrontRight = MotorConfig("FRD", 2, 2, MotorDirection.REVERSE)
                val BackLeft = MotorConfig("BLD", 1, 3, MotorDirection.FORWARD)
                val BackRight = MotorConfig("BRD", 2, 3, MotorDirection.REVERSE)
            }
        }
    }

    object IntakeSystem {
        object Motors {
            object Configs {
                val LeftIntake = MotorConfig("LIN", 1, 1, maxPow = 0.9)
                val RightIntake = MotorConfig("RIN", 2, 1, maxPow = 0.9)
            }

//            const val MaxPower = 0.9
        }
    }

    object OutTakeSystem {
        object Servos {
            object Configs {
                val GrabberServo = ServoConfig("GRB", 1, 2)
                val OuttakeServo = ServoConfig("OUT", 1, 4)
//                val CapstoneServo = ServoConfig("CAP", 1, 3)
            }
        }
    }

    object FoundationSystem {
        object Servos {
            object Configs {
                val FoundationServo1 = ServoConfig("LFG", 1, 0, 0.0, 0.5, ServoDirection.FORWARD)
                val FoundationServo2 = ServoConfig("RFG", 2, 0, 0.5, 1.0, ServoDirection.REVERSE)
            }
        }
    }

    object Lift {
        object Motors {
            val LeftLiftMotor = MotorConfig("LLF", 1, 0, direction = MotorDirection.REVERSE, maxPow = 0.7)
            val RightLiftMotor = MotorConfig("RLF", 2, 0, direction = MotorDirection.REVERSE, maxPow = 0.7)
        }

        object Switches {
            val BottomLimit = SwitchConfig("LIMD", 1, 0, true)
        }
    }

    object Odometry {
        val RightForward = OdometryConfig(RightIntake, -0.103, 8192.0, 2.0, true)
        val Strafe = OdometryConfig(BackRight, 0.0882, 8192.0, 2.0, true)
    }

    object AutonArms {
        object Servos {
            object Configs {
                val LeftArm = ServoConfig("LAA", 1, 1, max = 0.5, direction = Servo . Direction . REVERSE)
                val LeftClaw = ServoConfig("LAC", 1, 5)
                val LeftConfig = AutonArmConfig(LeftArm, LeftClaw)
                val RightArm = ServoConfig("RAA", 2, 1, min = 0.5)
                val RightClaw = ServoConfig("RAC", 2, 5, direction = Servo.Direction.REVERSE)
<<<<<<< HEAD
=======
                val RightConfig = AutonArmConfig(RightArm, RightClaw)
>>>>>>> d7f3725f3d1f64cd92886753c8d1efc284a9e2d8
            }
        }
    }
}