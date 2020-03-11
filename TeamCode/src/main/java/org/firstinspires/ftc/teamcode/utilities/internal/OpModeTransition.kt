package org.firstinspires.ftc.teamcode.utilities.internal

import com.qualcomm.ftccommon.CommandList
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.robocol.Command
import org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef
import org.firstinspires.ftc.teamcode.teleop.CompTeleOp
import kotlin.concurrent.thread
import kotlin.reflect.KClass

fun initOtherOpMode(opmode: KClass<CompTeleOp>) {
    val name = getOpModeName(opmode.java) ?: return
    initOpMode(name)
}

fun main() {
    val name = getOpModeName(CompTeleOp::class.java) ?: return
    println(name)
    val command = getInitCommand(name)
    println(command)
}

fun initOpMode(opMode: String) {
//    Log.e("handlingCommand", command.toString())
//    moeOpMode.iRequestOpModeStop()

    activityRef?.eventLoop?.opModeManager?.apply {
        thread {
//            stopActiveOpMode()
            initActiveOpMode(opMode)
        }

    }
}

fun getOpModeName(clazz: Class<*>): String? {
    var name = when {
        clazz.isAnnotationPresent(TeleOp::class.java) -> clazz.getAnnotation(TeleOp::class.java)?.name
        clazz.isAnnotationPresent(Autonomous::class.java) -> clazz.getAnnotation(Autonomous::class.java)?.name
        else -> clazz.simpleName
    }
    if (name?.trim() == "") name = clazz.simpleName
    return name
}


fun getInitCommand(annotation: String): Command {
    return Command(CommandList.CMD_INIT_OP_MODE, annotation)
}
