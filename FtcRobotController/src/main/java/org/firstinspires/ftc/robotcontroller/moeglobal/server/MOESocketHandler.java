package org.firstinspires.ftc.robotcontroller.moeglobal.server;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

public class MOESocketHandler {
    public static MOEServer server;

    public static void init(FtcRobotControllerActivity activity) {
        server = new MOEServer();
    }
}
