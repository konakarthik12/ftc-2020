package org.firstinspires.ftc.robotcontroller.moeglobal.sheets

//package org.firstinspires.ftc.robotcontroller.moeglobal.sheets
//
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity

//
class MOESheetsHandler {
//    lateinit var sheetsApi: MOESheetsAPI

    companion object {
        @JvmStatic
        fun init(activity: FtcRobotControllerActivity) {
            SheetAuthenticationManager.init(activity)
        }
    }
}

