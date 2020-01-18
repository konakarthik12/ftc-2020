package org.firstinspires.ftc.robotcontroller.moeglobal.sheets

import android.content.Context
<<<<<<< HEAD
=======
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
>>>>>>> d7f3725f3d1f64cd92886753c8d1efc284a9e2d8
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity

class MOESheetsHandler {
    companion object {
        @JvmStatic
        fun init(activity: FtcRobotControllerActivity) {
<<<<<<< HEAD
//            SheetsQu
=======

>>>>>>> d7f3725f3d1f64cd92886753c8d1efc284a9e2d8
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