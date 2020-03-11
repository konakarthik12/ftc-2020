package org.firstinspires.ftc.robotcontroller.moeglobal

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity
import java.lang.ref.WeakReference

object ActivityReferenceHolder {
    lateinit var activityRefHolder: WeakReference<FtcRobotControllerActivity>
    val activityRef: FtcRobotControllerActivity?
        get() = activityRefHolder.get()
}