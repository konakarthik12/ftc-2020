package org.firstinspires.ftc.robotcontroller.moeglobal;

import android.os.Environment;

import java.io.File;

public class FileUtils {

    private static String getFireCodePathFromUUID(String uuid) {
        return Environment.getExternalStorageDirectory() + "/firecode/" + uuid + ".dex";
    }

    public static File getFireCodeFileFromUUID(String uuid) {
        return new File(getFireCodePathFromUUID(uuid));
    }
}
