package org.firstinspires.ftc.robotcontroller.moeglobal.sheets.api

class MOESheetsApi {
    fun getSpreadSheet(id:String):MOESheet{
        return MOESheet(id)
    }
}