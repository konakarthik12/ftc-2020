package org.firstinspires.ftc.robotcontroller.moeglobal.server;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.ArrayMap;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.firebase.database.DatabaseReference;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import dalvik.system.DexClassLoader;
import org.firstinspires.ftc.robotcontroller.moeglobal.MOEFtcEventLoop;
import org.firstinspires.ftc.robotcontroller.moeglobal.opmodeloading.*;
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


    MOEServer(Context context) {
        super(new InetSocketAddress(0));

    }


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
        DexHandler.writeDex(message);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println("recieved: " + s);
//        String[] split = s.split("/");
        if (s.startsWith("ops")) {
            OpModeLoader.updateOpModes(s.substring(4));
        }

    }


    private static void refreshUI() {
        MOEFtcEventLoop eventLoop = activityRef.get().eventLoop;
        if (eventLoop == null) return;
        eventLoop.sendUIState();

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


}
