package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot

import com.qualcomm.robotcore.hardware.DigitalChannel.Mode.INPUT
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware.SwitchConfig

class MOESwitch(val config: SwitchConfig) {
    private val channel = config.getDevice()

    init {
        channel.mode = INPUT
    }

    val isPressed: Boolean
        get() = channel.state
}