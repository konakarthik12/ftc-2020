package org.firstinspires.ftc.robotcontroller.moeglobal.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MOEDatabase {
    public static DatabaseReference status;
    public static DatabaseReference dataRef;
    public static DatabaseReference commands;

    public static void init() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        fillRefs(database);
        handleStatus();
    }

    private static void fillRefs(FirebaseDatabase database) {
        dataRef = database.getReference("driver-station-link");
        status = dataRef.child("status");
        commands = dataRef.child("RCCommands");
    }


    private static void handleStatus() {
      status.onDisconnect().setValue(false);
      status.setValue(true);
    }


}
