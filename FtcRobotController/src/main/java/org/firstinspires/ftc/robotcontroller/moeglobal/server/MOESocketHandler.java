package org.firstinspires.ftc.robotcontroller.moeglobal.server;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.internal.opmode.RegisteredOpModes;

public class MOESocketHandler {
    public static MOEServer moeWebServer;

    public static void init(FtcRobotControllerActivity activity) {
        moeWebServer = new MOEServer(activity);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RegisteredOpModes.getInstance().waitOpModesRegistered();
                moeWebServer.start();
            }
        }).start();

    }


}
