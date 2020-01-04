package org.firstinspires.ftc.teamcode.utilities.internal

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.constants.OpModeInterface


infix fun OpModeInterface.wait(milliseconds: Number) {
    val time = ElapsedTime()
    val length = milliseconds.toLong()
    while (time.milliseconds() < length && iOpModeIsActive());
}
