package org.firstinspires.ftc.robotcontroller.moeglobal.server;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

public class MOESocketHandler {
    public static MOEServer moeWebServer;

    public static void init(FtcRobotControllerActivity activity) {
        moeWebServer = new MOEServer(activity);
        moeWebServer.start();
    }


}
