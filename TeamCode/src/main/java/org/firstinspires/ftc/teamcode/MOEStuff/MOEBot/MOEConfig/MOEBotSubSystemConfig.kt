package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig

data class MOEBotSubSystemConfig(val useGyro: Boolean = true,
                                 val useCamera: Boolean = false,
                                 var useSlam: Boolean = false,
                                 var useOdometry: Boolean = false)