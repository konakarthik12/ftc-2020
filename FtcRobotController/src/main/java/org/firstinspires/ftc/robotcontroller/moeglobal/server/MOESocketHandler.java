package org.firstinspires.ftc.robotcontroller.moeglobal.server;

import android.content.Context;
import com.qualcomm.ftcrobotcontroller.R;
import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.internal.opmode.RegisteredOpModes;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

public class MOESocketHandler {
    public static MOEServer moeWebServer;

    public static void init(FtcRobotControllerActivity activity) {
        moeWebServer = new MOEServer(null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RegisteredOpModes.getInstance().waitOpModesRegistered();
                moeWebServer.start();
            }
        }).start();

    }

//    public static SSLContext getSSLContext(Context context) {
//        String storeType = "JKS";
//        String storePassword = "prerys12";
//        String keyPassword = "prerys12";
//
//        KeyStore ks;
//        SSLContext sslContext;
//        try {
//            for (Provider provider : Security.getProviders()) {
//
//            }
//            ks = KeyStore.getInstance(storeType);
//            InputStream in = context.getResources().openRawResource(R.raw.keystore);
//            ks.load(in, storePassword.toCharArray());
//            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
//            kmf.init(ks, keyPassword.toCharArray());
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
//            tmf.init(ks);
//            sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
//        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException e) {
//            throw new IllegalArgumentException(e);
//        }
//        return sslContext;
//    }

}
