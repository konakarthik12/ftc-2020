package org.firstinspires.ftc.robotcontroller.moeglobal.sheets.api

import com.google.api.services.sheets.v4.model.ValueRange
import org.firstinspires.ftc.robotcontroller.moeglobal.sheets.MOESheetsHandler.Companion.internalApi

class MOESheet(private val id: String) {
    fun appendRow(range: String, data: List<Any>) {
        internalApi.spreadsheets().values().append(id, range, data.toValueRow())
    }
}

fun List<Any>.toValueRow(): ValueRange {
    val apply = ValueRange().also {
        it.setValues(listOf(this))
        it.majorDimension = "COLUMNS"
    }
    return apply
}