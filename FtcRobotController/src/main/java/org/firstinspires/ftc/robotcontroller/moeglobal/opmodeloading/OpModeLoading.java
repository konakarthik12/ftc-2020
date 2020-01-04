package org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.ftcrobotcontroller.R;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.moeglobal.firebase.MOEDatabase;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;

import static org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef;

public class OpModeLoading {

    public static void playInstalledSound() {
        SoundPlayer.getInstance().startPlaying(activityRef.get(), R.raw.firecode);
    }


}
