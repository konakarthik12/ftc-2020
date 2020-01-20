package org.firstinspires.ftc.robotcontroller.moeglobal

fun ByteArray.divideArray(chunkSize: Int): MutableList<ByteArray> = ArrayHandler.divideArray(this, chunkSize)