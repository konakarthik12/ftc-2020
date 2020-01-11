package org.firstinspires.ftc.teamcode.constants

import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.MotorConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.ServoConfig

object MOEHardwareConstants {
    object DriveTrain {
        object Motors {
            object Configs {
                val FrontLeft = MotorConfig("FLD", 1, 2, DcMotorSimple.Direction.FORWARD)
                val FrontRight = MotorConfig("FRD", 2, 2, DcMotorSimple.Direction.REVERSE)
                val BackLeft = MotorConfig("BLD", 1, 3, DcMotorSimple.Direction.FORWARD)
                val BackRight = MotorConfig("BRD", 2, 3, DcMotorSimple.Direction.REVERSE)
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
                val CapstoneServo = ServoConfig("CAP", 1, 3)
                val OuttakeServo = ServoConfig("OUT", 1, 4)
            }
        }
    }

    object FoundationSystem {
        object Servos {
            object Configs {
                val FoundationServo1 = ServoConfig("LFG", 1, 0, 0.0, 0.5, Servo.Direction.FORWARD)
                val FoundationServo2 = ServoConfig("RFG", 2, 0, 0.5, 1.0, Servo.Direction.REVERSE)
            }
        }
    }

    object Lift {
        object Motors {
            val LeftLiftMotor = MotorConfig("LLF", 1, 0, direction = DcMotorSimple.Direction.REVERSE, maxPow = 0.7)
            val RightLiftMotor = MotorConfig("RLF", 2, 0, direction = DcMotorSimple.Direction.REVERSE, maxPow = 0.7)
        }

    }
}