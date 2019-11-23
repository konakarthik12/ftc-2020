package org.firstinspires.ftc.robotcontroller.moeglobal.server;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.firebase.database.DatabaseReference;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import dalvik.system.DexClassLoader;
import org.firstinspires.ftc.robotcontroller.moeglobal.MOEFtcEventLoop;
import org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading.OpModeLoading;
import org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading.ReflectionHolder;
import org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading.TeamLessClassLoader;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta.Flavor;
import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMetaAndClass;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Map;

import static android.content.Context.WIFI_SERVICE;
import static org.firstinspires.ftc.robotcontroller.moeglobal.ActivityReferenceHolder.activityRef;
import static org.firstinspires.ftc.robotcontroller.moeglobal.firebase.MOEDatabase.dataRef;

public class MOEServer extends WebSocketServer {

    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;
    private final String absolutePath;
    private final ClassLoader contextClassLoader;
    private TeamLessClassLoader parent;

    MOEServer(Context context) {
        super(new InetSocketAddress(0));
        sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        absolutePath = ContextCompat.getCodeCacheDir(context).getAbsolutePath();
        contextClassLoader = context.getClassLoader();

    }

    File dexFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/firecode/classes.dex");

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        writeDex(message);
        conn.send("ops");
    }

    private void writeDex(ByteBuffer message) {
        try {
            FileChannel fc = new FileOutputStream(dexFile).getChannel();
            fc.write(message);
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
//        String[] split = s.split("/");
        if (s.startsWith("ops")) {
            saveOpModes(s.substring(4));
        }

    }

    private void saveOpModes(String substring) {
        String[] split = substring.split("\n");
        saveOpModes("Autonomous", split[0]);
        saveOpModes("TeleOp", split[1]);
        editor.apply();
        pushOpModes(split[0], split[1]);
        //        editor.putInt(getString(R.string.saved_high_score_key), newHighScore);

    }

    private void pushOpModes(String auto, String tele) {
//        editor
        DexClassLoader classLoader = getClassLoader();
        Map<String, OpModeMetaAndClass> opModeMetaAndClassMap = new ArrayMap<>();
        String[] autoDetails = auto.split("/");
        for (int i = 0; i < autoDetails.length; i += 2) {
            opModeMetaAndClassMap.put(autoDetails[i], getOpModeMetaAndClass(classLoader, autoDetails[i], autoDetails[i + 1], true));
        }
        String[] teleDetails = tele.split("/");
        for (int i = 0; i < teleDetails.length; i += 2) {
            opModeMetaAndClassMap.put(teleDetails[i], getOpModeMetaAndClass(classLoader, teleDetails[i], teleDetails[i + 1], false));
        }
        ReflectionHolder.replaceOpModes(opModeMetaAndClassMap);
        refreshUI();
        OpModeLoading.playInstalledSound();
        Log.e("done", "done");
    }

    private void refreshUI() {
        MOEFtcEventLoop eventLoop = activityRef.get().eventLoop;
        if (eventLoop == null) return;
        eventLoop.refreshUI();

    }

    private OpModeMetaAndClass getOpModeMetaAndClass(DexClassLoader classLoader, String name, String path, boolean isAuton) {
        return new OpModeMetaAndClass(new OpModeMeta(name, isAuton ? Flavor.AUTONOMOUS : Flavor.TELEOP), getClass(classLoader, path));
    }

    private Class<OpMode> getClass(DexClassLoader classLoader, String path) {
        try {
            return (Class<OpMode>) classLoader.loadClass(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private DexClassLoader getClassLoader() {
        parent = new TeamLessClassLoader(contextClassLoader);
        return new DexClassLoader(dexFile.getAbsolutePath(), absolutePath, null, parent);
    }


    private void saveOpModes(String type, String s) {
        editor.putString(type, s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {
        Log.e("websockets", String.valueOf(getPort()));

        DatabaseReference ws = dataRef.child("ws");
//        WifiManager wm = (WifiManager) activityRef.get().getSystemService(WIFI_SERVICE);
//        wm.getDhcpInfo()
//        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        ws.child("ip").setValue(getIpAddress());
        ws.child("port").setValue(getPort());
    }

    private Object getIpAddress() {
        Context context = activityRef.get();
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        // Convert little-endian to big-endianif needed
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFIIP", "Unable to get host address.");
            ipAddressString = null;
        }

        return ipAddressString;
    }

    @SuppressWarnings("ConstantConditions")
    public void fakePush() {
        pushOpModes(sharedPref.getString("Autonomous", ""), sharedPref.getString("TeleOp", ""));
    }
}
