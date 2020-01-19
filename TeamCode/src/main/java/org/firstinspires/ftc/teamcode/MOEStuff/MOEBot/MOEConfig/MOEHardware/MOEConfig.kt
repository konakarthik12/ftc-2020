package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware

import android.util.Log
import com.qualcomm.robotcore.hardware.HardwareDevice
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap

val devices = mutableMapOf<String, HardwareDevice>()

open class MOEConfig<Type : HardwareDevice>(val name: String, val hub: Int, val port: Int) {

    fun getDevice(): Type {

        val fullName = name + hub + port
        return devices.getOrPut(fullName) {
            hardwareMap.get(fullName)
        } as Type
    }
}
