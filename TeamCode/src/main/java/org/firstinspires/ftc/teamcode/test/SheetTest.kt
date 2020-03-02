package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcontroller.moeglobal.sheets.MOESheetsHandler

@Disabled
@TeleOp
class SheetTest : MOERegularTest() {
    private val spreadsheetId = "1uNS9gqPgAniJ5dFc8laeWUjAYgr0QJhPaebkPoGI8zs"
    override fun run() {
        MOESheetsHandler.api.getSpreadSheet(spreadsheetId).appendRow("Sheet1", listOf("test"))
    }
}