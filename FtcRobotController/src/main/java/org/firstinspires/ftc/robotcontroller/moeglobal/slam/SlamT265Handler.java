package org.firstinspires.ftc.robotcontroller.moeglobal.slam;

import android.hardware.usb.*;
import android.util.Log;
import org.firstinspires.ftc.robotcontroller.moeglobal.firebase.MOEConfig;
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOEServer;
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOESocketHandler;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static android.hardware.usb.UsbConstants.USB_DIR_OUT;
import static android.os.Process.THREAD_PRIORITY_BACKGROUND;
import static android.os.Process.setThreadPriority;
import static org.firstinspires.ftc.robotcontroller.moeglobal.slam.Constants.*;

public class SlamT265Handler {
    private volatile boolean initCodeSent = false;
    private UsbDeviceConnection connection;
    private UsbInterface usbInterface;
    private UsbEndpoint control, outEndpoint2, outEndpoint3, inEndpoint129, inEndpoint130, inEndpoint131;
    private byte[] tempSlam = new byte[104];
    private volatile float[] curPose = new float[3];
    private volatile boolean isRunning = false;
    private double[] quatAngle = new double[4];
    private boolean needsRestart = false;

    SlamT265Handler(UsbDevice device) {
        initVariables(device);
    }

    public static UsbDevice getDevice() {
        return SlamUsbHandler.getDevice(T265_VID);
    }

    private void initVariables(UsbDevice device) {
        connection = SlamUsbHandler.getDeviceConnection(device);
        usbInterface = device.getInterface(0);
        loadEndpoints();
    }

    private void loadEndpoints() {
        for (int k = 0; k < usbInterface.getEndpointCount(); k++) {
            UsbEndpoint endpoint = usbInterface.getEndpoint(k);
            boolean outgoing = endpoint.getDirection() == USB_DIR_OUT;
            if (outgoing) {
                switch (endpoint.getAddress()) {
                    case 1:
                        control = endpoint;
                        break;
                    case 2:
                        outEndpoint2 = endpoint;
                        break;
                    case 3:
                        outEndpoint3 = endpoint;
                        break;
                }
            } else {
                switch (endpoint.getAddress()) {
                    case 129:
                        if (UsbConstants.USB_DIR_IN == endpoint.getDirection())
                            inEndpoint129 = endpoint;
                        break;
                    case 130:
                        if (UsbConstants.USB_DIR_IN == endpoint.getDirection())
                            inEndpoint130 = endpoint;
                        break;
                    case 131:
                        if (UsbConstants.USB_DIR_IN == endpoint.getDirection())
                            inEndpoint131 = endpoint;
                        break;
                }
            }
        }
    }

    public void startStream() {
        if (!initCodeSent) {
            connection.claimInterface(usbInterface, true);
            sendInitCode();
            initCodeSent = true;
        }

        isRunning = true;
        new Thread(new SlamRunnable()).start();
    }

    private void updateSlam() {
        connection.bulkTransfer(inEndpoint131, tempSlam, 0, tempSlam.length, 100);
        ByteBuffer order = ByteBuffer.wrap(tempSlam, 8, 7 * 4).order(ByteOrder.LITTLE_ENDIAN);

        for (int i = 0; i < 3; i++) {
            curPose[i] = order.getFloat();
        }
        for (int i = 0; i < 4; i++) {
            quatAngle[i] = order.getFloat();
        }
    }

    private void sendInitCode() {
        sendCode(SLAM_CONTROL);
        sendCode(POSE_CONTROL);
        sendCode(DEV_START);
    }

    private void sendCode(byte[] arr) {
        byte[] bytes = new byte[8];
        connection.bulkTransfer(outEndpoint2, arr, arr.length, SMALL_TIMEOUT);
        connection.bulkTransfer(inEndpoint130, bytes, 8, 100);
    }

    public void killStream() {
        isRunning = false;
    }

//    private void closeConnection() {
//        connection.releaseInterface(usbInterface);
//        connection.close();
//    }

    public float[] getCurPose() {
        return curPose;
    }

    public double[] getQuatAngle() {
        return quatAngle;
    }

    public void restart() {
        needsRestart = true;
    }

    public class SlamRunnable implements Runnable {
        public void run() {
            setThreadPriority(THREAD_PRIORITY_BACKGROUND);
            while (isRunning) {
                updateSlam();
                checkRestart();
            }
//            closeConnection();
        }
    }

    private void checkRestart() {
        if (needsRestart) {
            Log.e("restarting", "restart");
            sendCode(DEV_STOP);
//            try {
//                //TODO: Maybe remove
////                Thread.sleep();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            sendInitCode();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        needsRestart = false;
    }
}
