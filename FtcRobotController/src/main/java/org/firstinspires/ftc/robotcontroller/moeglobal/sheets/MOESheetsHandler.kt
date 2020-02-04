package org.firstinspires.ftc.robotcontroller.moeglobal.sheets

//package org.firstinspires.ftc.robotcontroller.moeglobal.sheets
//
import com.google.api.services.sheets.v4.Sheets
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity
import org.firstinspires.ftc.robotcontroller.moeglobal.sheets.api.MOESheetsApi

//
class MOESheetsHandler {
//    lateinit var sheetsApi: MOESheetsAPI

    companion object {
        lateinit var api: MOESheetsApi
        lateinit var internalApi: Sheets
        @JvmStatic
        fun init(activity: FtcRobotControllerActivity) {
            SheetAuthenticationManager.init(activity)
        }

        fun useApi(internalApi: Sheets) {
            this.internalApi = internalApi
            api = MOESheetsApi()
        }
    }
}

