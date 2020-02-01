package org.firstinspires.ftc.robotcontroller.moeglobal.sheets

//
//
import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.android.gms.drive.Drive
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.sheets.v4.SheetsScopes
import kotlinx.android.synthetic.main.activity_ftc_controller.*
import org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef

object SheetAuthenticationManager {
    lateinit var googleSignInClient: GoogleSignInClient
    private val SCOPES = arrayOf(SheetsScopes.SPREADSHEETS_READONLY)

    private val signInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(Scope(SheetsScopes.SPREADSHEETS_READONLY))
                .requestScopes(Scope(SheetsScopes.SPREADSHEETS))
                .requestScopes(Drive.SCOPE_FILE)
                .requestEmail()
                .build()
    }

    fun init(activity: Activity) {
        initButton(activity)
        googleSignInClient = GoogleSignIn.getClient(activity, signInOptions)

        if (alreadyAuth()) {
            createApi()
        }
    }

    private fun initButton(activity: Activity) {
        activity.sheetsSignInButton.setOnClickListener {
            callSignInActivity(activity)
        }
    }

    val RQ_GOOGLE_SIGN_IN = 365
    private fun callSignInActivity(activity: Activity) {
        activity.startActivityForResult(googleSignInClient.signInIntent, RQ_GOOGLE_SIGN_IN)
    }

    private fun createApi() {

        val googleAccountCredential = GoogleAccountCredential
                .usingOAuth2(activityRef.get(), SCOPES.toList())
                .setBackOff(ExponentialBackOff())
                .setSelectedAccount(getLastSignedAccount()?.account)

    }


    private fun getLastSignedAccount(): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(activityRef.get())
    }

    fun alreadyAuth(): Boolean {
        return getLastSignedAccount() != null
//        activity.startActivityForResult(googleSignInClient.signInIntent, 365)
    }

    @JvmStatic
    fun handleSignIn(resultCode: Int) {
        if (resultCode == Activity.RESULT_OK) {
            loginSuccessful()
        } else {
            loginFailed()
        }
    }

    private fun loginFailed() {
        activityRef.get()?.sheetsSignInButton.text = "Sign In Failed"
    }

    private fun loginSuccessful() {
        createApi()
    }

}