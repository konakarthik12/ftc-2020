package org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading;

import android.content.Context;
import android.content.SharedPreferences;
import com.qualcomm.ftcrobotcontroller.R;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

public class SharedPrefHandler {
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;

    public static void init(FtcRobotControllerActivity context) {
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    static void saveOpModes(String[] split) {
        saveOpModes("Autonomous", split[0]);
        saveOpModes("TeleOp", split[1]);
        editor.apply();

    }

    private static void saveOpModes(String type, String s) {
        editor.putString(type, s);
    }

    public static String[] getOpModes() {
        return new String[]{getOpMode("Autonomous"), getOpMode("TeleOp")};
    }

    private static String getOpMode(String type) {
        String string = sharedPref.getString(type, "");
        return string;
    }
}
