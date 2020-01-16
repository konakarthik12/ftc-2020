package org.firstinspires.ftc.teamcode.constants

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.MotorConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.OdometryConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.ServoConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.SwitchConfig
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.IntakeSystem.Motors.Configs.LeftIntake
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.IntakeSystem.Motors.Configs.RightIntake
import org.firstinspires.ftc.teamcode.constants.MOEHardwareConstants.Lift.Motors.RightLiftMotor
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
        val RightForward = OdometryConfig(RightIntake, 0.0, 8192.0, 2.0)
        val Strafe = OdometryConfig(LeftIntake, 0.0, 8192.0, 2.0)
    }
}