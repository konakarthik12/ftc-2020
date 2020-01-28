package org.firstinspires.ftc.robotcontroller.moeglobal.sheets

//
//
import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.android.gms.drive.Drive
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.sheets.v4.SheetsScopes

class SheetAuthenticationManager(private val activity: Activity) {
    init {
        launchAuth()
    }

    val signInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Scope(SheetsScopes.SPREADSHEETS_READONLY))
                .requestScopes(Scope(SheetsScopes.SPREADSHEETS))
                .requestScopes(Drive.SCOPE_FILE)
                .requestEmail()
                .build()
    }

    val googleSignInClient = GoogleSignIn.getClient(activity, signInOptions)
    val SCOPES = arrayOf(SheetsScopes.SPREADSHEETS_READONLY)

    val googleAccountCredential = GoogleAccountCredential
            .usingOAuth2(activity, SCOPES.toList())
            .setBackOff(ExponentialBackOff())
            .setSelectedAccount(getLastSignedAccount()?.account)


    fun getLastSignedAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(activity)
    }

    fun launchAuth() {
        activity.startActivityForResult(googleSignInClient.signInIntent, RQ)
    }

}
