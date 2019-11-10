package org.firstinspires.ftc.teamcode.external

actual fun Double.format(digits: Int): String {
    return "%.${digits}f".format(this)

}