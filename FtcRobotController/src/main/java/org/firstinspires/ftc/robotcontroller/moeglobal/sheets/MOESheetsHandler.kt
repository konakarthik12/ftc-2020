package org.firstinspires.ftc.robotcontroller.moeglobal.sheets

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity

class MOESheetsHandler {
    companion object {
        @JvmStatic
        fun init(activity: FtcRobotControllerActivity) {

        }
    }

    private fun requestSignIn(context: Context) {
        /*
        GoogleSignIn.getLastSignedInAccount(context)?.also { account ->
            Timber.d("account=${account.displayName}")
        }
         */

//        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//         .requestEmail()
//             .requestScopes(Scope(Scope.SPREADSHEETS_READONLY))
//                .requestScopes(Scope(SheetsScopes.SPREADSHEETS))
//                .build()
//        val client = GoogleSignIn.getClient(context, signInOptions)
//
//        startActivityForResult(client.signInIntent, REQUEST_SIGN_IN)
    }
}