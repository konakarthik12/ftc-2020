package org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading;

import android.os.Environment;
import android.util.ArrayMap;
import android.util.Log;
import androidx.core.content.ContextCompat;
import dalvik.system.DexClassLoader;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMetaAndClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

public class DexHandler {
    private static File dexFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/firecode/classes.dex");
    private static String absolutePath;

    private static ClassLoader contextClassLoader;

    public static void init(FtcRobotControllerActivity context) {
        dexFile.getParentFile().mkdirs();
        absolutePath = ContextCompat.getCodeCacheDir(context).getAbsolutePath();
        contextClassLoader = context.getClassLoader();

    }

    private static DexClassLoader getClassLoader() {
        TeamLessClassLoader parent = new TeamLessClassLoader(contextClassLoader);
        return new DexClassLoader(dexFile.getAbsolutePath(), absolutePath, null, parent);
    }

    public static void writeDex(ByteBuffer message) {
        try {
//            Log.e("happening", dexFile.getAbsolutePath());
            FileChannel fc = new FileOutputStream(dexFile).getChannel();
            fc.write(message);
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void updateOpModes(String[] opmodes) {
//        editor
        DexClassLoader classLoader = getClassLoader();
        Map<String, OpModeMetaAndClass> metaMap = DexOpModeHandler.getMetaMap(opmodes, classLoader);

        ReflectionHolder.replaceOpModes(metaMap);
//        Log.e("done", "done");
    }


}
