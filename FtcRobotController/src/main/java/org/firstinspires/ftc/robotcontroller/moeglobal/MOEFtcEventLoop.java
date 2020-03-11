package org.firstinspires.ftc.robotcontroller.moeglobal;

import android.app.Activity;
import com.qualcomm.ftccommon.FtcEventLoop;
import com.qualcomm.ftccommon.UpdateUI;
import com.qualcomm.hardware.HardwareFactory;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.robocol.Command;
import org.firstinspires.ftc.robotcore.internal.network.CallbackResult;

public class MOEFtcEventLoop extends FtcEventLoop {


    public MOEFtcEventLoop(HardwareFactory hardwareFactory, OpModeRegister userOpmodeRegister, UpdateUI.Callback callback, Activity activityContext) {
        super(hardwareFactory, userOpmodeRegister, callback, activityContext);

    }

    @Override
    public CallbackResult processCommand(Command command) throws InterruptedException, RobotCoreException {
        return super.processCommand(command);
    }

    @Override
    public void sendUIState() {
        super.sendUIState();
    }
}
