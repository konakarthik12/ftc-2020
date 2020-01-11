package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEHardware

import com.qualcomm.robotcore.hardware.HardwareDevice
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap

open class MOEConfig<Type : HardwareDevice>(val name: String, val hub: Int, val port: Int, val java: Class<Type>) {

    fun getDevice(): Type {
//        hardwareMap.ge
        return hardwareMap.get(name + hub + port) as Type
    }
}
