package org.firstinspires.ftc.teamcode.utilities

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOEOpMode
import org.firstinspires.ftc.teamcode.constants.OpModeInterface
import java.util.concurrent.TimeUnit


infix fun OpModeInterface.wait(milliseconds: Number) {
    val time = ElapsedTime()
    val length = milliseconds.toLong()
    while (time.milliseconds() < length && iOpModeIsActive());
}
