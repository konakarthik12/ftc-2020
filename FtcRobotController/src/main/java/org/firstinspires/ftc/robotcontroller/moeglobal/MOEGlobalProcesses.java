package org.firstinspires.ftc.robotcontroller.moeglobal;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcontroller.moeglobal.firebase.MOEFirebase;
import org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading.OpModeLoader;
import org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading.ReflectionHolder;
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOESocketHandler;
import org.firstinspires.ftc.robotcontroller.moeglobal.sheets.MOESheetsHandler;
import org.firstinspires.ftc.robotcontroller.moeglobal.slam.SlamHandler;

import java.lang.ref.WeakReference;

import static org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRefHolder;

public class MOEGlobalProcesses {


    public static void preInit(FtcRobotControllerActivity activity) {
        activityRefHolder = new WeakReference<>(activity);
        OpModeLoader.init(activity);
        ReflectionHolder.init();
        MOEFirebase.init(activity);
        MOESocketHandler.init(activity);
        SlamHandler.init(activity);
    }

    public static void postLayoutInit(FtcRobotControllerActivity activity) {
        MOESheetsHandler.init(activity);

    }
}
