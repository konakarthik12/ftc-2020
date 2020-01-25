package org.firstinspires.ftc.robotcontroller.moeglobal.firebase;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import org.firstinspires.ftc.robotcontroller.moeglobal.firebase.commands.MOECommands;

public class MOEFirebase {
    public static void init(Context context) {
        FirebaseApp.initializeApp(context);
        MOEDatabase.init();
        MOECommands.init();
        MOEConfig.init();
    }
}
