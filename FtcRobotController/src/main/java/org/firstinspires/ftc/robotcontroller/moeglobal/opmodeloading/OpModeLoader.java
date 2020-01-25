package org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading;

import android.os.Environment;
import androidx.core.content.ContextCompat;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.ftcrobotcontroller.R;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import java.io.File;

import static org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef;

public class OpModeLoader {


    private static String absolutePath;

    public static void init(FtcRobotControllerActivity context) {
        ReflectionHolder.init();
        DexHandler.init(context);
        SharedPrefHandler.init(context);
//        fakeUpdate();
    }

    private static void fakeUpdate() {
        DexHandler.updateOpModes(SharedPrefHandler.getOpModes());
    }

    public static void playInstalledSound() {
        SoundPlayer.getInstance().startPlaying(activityRef.get(), R.raw.firecode);
    }

    public static void updateOpModes(String substring) {
        String[] split = substring.split("\n");
        DexHandler.updateOpModes(split);
        SharedPrefHandler.saveOpModes(split);

        playInstalledSound();
        updateUI();
    }

    private static void updateUI() {
        FtcRobotControllerActivity ftcRobotControllerActivity = activityRef.get();
        if (ftcRobotControllerActivity == null) return;
        ftcRobotControllerActivity.eventLoop.sendUIState();
    }

}
