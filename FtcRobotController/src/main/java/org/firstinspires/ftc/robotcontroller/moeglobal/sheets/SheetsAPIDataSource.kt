package org.firstinspires.ftc.robotcontroller.moeglobal.sheets

import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.Spreadsheet
import java.util.*

class SheetsAPIDataSource(private val authManager: SheetAuthenticationManager) {

//    private val sheetsAPI: Sheets =
//            Sheets.Builder(AndroidHttp.newCompatibleTransport(),
//                    JacksonFactory.getDefaultInstance(),
//                    authManager.googleAccountCredential)
//                    .setApplicationName("test")
//                    .build()


//    override fun readSpreadSheet(spreadsheetId: String,
//                                 spreadsheetRange: String): Single<List<Person>> {
//        return Observable
//                .fromCallable{
//                    val response = sheetsAPI.spreadsheets().values()
//                            .get(spreadsheetId, spreadsheetRange)
//                            .execute()
//                    response.getValues() }
//                .flatMapIterable { it -> it }
//                .map { Person(it[0].toString(), it[4].toString()) }
//                .toList()
//    }

//    override fun createSpreadsheet(spreadSheet : Spreadsheet) : Observable<SpreadsheetInfo> {
//        return Observable
//                .fromCallable{
//                    val response =
//                            sheetsAPI
//                                    .spreadsheets()
//                                    .create(spreadSheet)
//                                    .execute()
//                    response }
//                .map { SpreadsheetInfo(it[KEY_ID] as String, it[KEY_URL] as String) }
//    }

    companion object {
        val KEY_ID = "spreadsheetId"
        val KEY_URL = "spreadsheetUrl"
    }
}