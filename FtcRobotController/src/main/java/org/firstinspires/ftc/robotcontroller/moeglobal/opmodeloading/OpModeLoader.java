package org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.ftcrobotcontroller.R;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import static org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRefHolder;

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
        SoundPlayer.getInstance().stopPlayingAll();
//        SoundPlayer.getInstance().setMasterVolume(1.0f);
        SoundPlayer.getInstance().startPlaying(activityRefHolder.get(), R.raw.firecode, new SoundPlayer.PlaySoundParams(false), null, null);
//        SoundPlayer.getInstance().setMasterVolume(0.0f);

    }

    public static void updateOpModes(String substring) {
        String[] split = substring.split("\n");
        DexHandler.updateOpModes(split);
        SharedPrefHandler.saveOpModes(split);

        playInstalledSound();
        updateUI();
    }

    private static void updateUI() {
        FtcRobotControllerActivity ftcRobotControllerActivity = activityRefHolder.get();
        if (ftcRobotControllerActivity == null) return;
        ftcRobotControllerActivity.eventLoop.sendUIState();
    }

}
