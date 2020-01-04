package org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading;

import android.util.ArrayMap;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.storage.FileDownloadTask;
import org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder;
import org.firstinspires.ftc.robotcontroller.moeglobal.MOEValueListener;
import org.firstinspires.ftc.robotcontroller.moeglobal.firebase.MOEDatabase;
import org.firstinspires.ftc.robotcontroller.moeglobal.firebase.MOEStorage;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;

import java.util.Map;

public class OpModesFrame {
    public static OpModeReadiedData instance;
//    public static OpModeClassedData classedInstance;






    public static class OpModeFrameData {
        public String uuid;
        public Map<String, FirebaseOpModeMeta> allOpModes;

    }

    public static class OpModeReadiedData {
        public String uuid;
        public Map<String, OpModeMeta> opModes = new ArrayMap<>();

        public OpModeReadiedData(OpModeFrameData frameData) {
            uuid = frameData.uuid;
            if (frameData.allOpModes == null) {
                Log.e("empty", "firebase opmodes empty");
                return;
            }
            for (Map.Entry<String, FirebaseOpModeMeta> opMode : frameData.allOpModes.entrySet()) {
                opModes.put(opMode.getValue().qualifiedPath, opMode.getValue().getOpModeMeta(opMode.getKey()));
            }
        }
    }

}
