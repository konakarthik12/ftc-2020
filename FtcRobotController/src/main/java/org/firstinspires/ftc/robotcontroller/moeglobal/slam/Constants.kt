package org.firstinspires.ftc.robotcontroller.moeglobal.slam

object Constants {
    var LIZARD_VID = 0x03e7
    var T265_VID = 0x8087
    var CHUNK_SIZE = 512 * 32
    var SMALL_TIMEOUT = 100
    var SLAM_CONTROL = byteArrayOf(0x08, 0x00, 0x00, 0x00, 0x06, 0x10, 0x01, 0x06, 0)
    var POSE_CONTROL = byteArrayOf(0x09, 0, 0, 0, 0x02, 0x20, 0, 0, 0)
    var DEV_START = byteArrayOf(0x08, 0x00, 0x00, 0x00, 0x12, 0x00, 0x00, 0x00, 0)
    var OUTPUT_X_OFFSET = 8
    var OUTPUT_Y_OFFSET = 12
    var OUTPUT_Z_OFFSET = 16
    //I'm guessing
    var DEV_STOP = byteArrayOf(0x08, 0x00, 0x00, 0x00, 0x13, 0x00, 0x00, 0x00, 0)
}