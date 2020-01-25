package org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading;

import android.util.ArrayMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMetaAndClass;

import java.util.Map;

import static org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta.Flavor.AUTONOMOUS;
import static org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta.Flavor.TELEOP;

public class DexOpModeHandler {
    public static Map<String, OpModeMetaAndClass> getMetaMap(String[] opmodes, ClassLoader classLoader) {
        Map<String, OpModeMetaAndClass> opModeMetaAndClassMap = new ArrayMap<>();
        String[] autoDetails = opmodes[0].split("/");

        for (int i = 0; i < autoDetails.length; i += 2) {
            opModeMetaAndClassMap.put(autoDetails[i], getOpModeMetaAndClass(classLoader, autoDetails[i], autoDetails[i + 1], AUTONOMOUS));
        }
        String[] teles = opmodes[1].split("/");

        for (int i = 0; i < teles.length; i += 2) {
            opModeMetaAndClassMap.put(autoDetails[i], getOpModeMetaAndClass(classLoader, autoDetails[i], autoDetails[i + 1], TELEOP));
        }
        return opModeMetaAndClassMap;
    }

    private static OpModeMetaAndClass getOpModeMetaAndClass(ClassLoader classLoader, String name, String path, OpModeMeta.Flavor flavor) {
        return new OpModeMetaAndClass(new OpModeMeta(name, flavor), getClass(classLoader, path));
    }

    private static Class<OpMode> getClass(ClassLoader classLoader, String path) {
        try {
            return (Class<OpMode>) classLoader.loadClass(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
