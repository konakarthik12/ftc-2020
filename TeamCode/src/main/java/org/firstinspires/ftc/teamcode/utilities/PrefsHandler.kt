package org.firstinspires.ftc.teamcode.utilities

import android.content.Context
import org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef
import java.lang.reflect.Array.setFloat

object PrefsHandler {
    fun setFloat(pref: PrefKeys, value: Float) {
        prefs?.edit()?.putFloat(pref.toString(), value)?.apply()
    }

    fun setDouble(pref: PrefKeys, value: Double) = setFloat(pref, value.toFloat())
    fun getDouble(pref: PrefKeys, defaultValue: Double = 0.0) = getFloat(pref, defaultValue.toFloat())?.toDouble()
    fun getFloat(pref: PrefKeys, defaultValue: Float = 0f): Float? {
        return prefs?.getFloat(pref.toString(), defaultValue)
    }

    val prefs by lazy {
        activityRef?.getPreferences(Context.MODE_PRIVATE)
    }

}

enum class PrefKeys {
    AUTON_GYRO_FINAL_ANGLE
}