package org.firstinspires.ftc.teamcode

import android.view.View
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEConfig.MOEBotSubSystemConfig
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp

@Autonomous
class AutonSetup : MOETeleOp() {
//    lateinit var crop_preview: View

    override fun initOpMode() {

//        crop_preview = activityRef.get()?.findViewById(R.id.crop_preview)!!
//        activityRef.get()?.runOnUiThread {
//            crop_preview.visibility = VISIBLE
//        }
    }

    //    val timer = ElapsedTime()
    override fun mainLoop() {
//        if (timer.seconds() < 5) return
//        timer.reset()
//        activityRef.get()?.runOnUiThread {
//            crop_preview.background = BitmapDrawable(hardwareMap.appContext.resources, robot.vuforia.getCroppedBitmap(SKYSTONE_CROP))
//
//        }
    }

    override fun getRobotSubSystemConfig(): MOEBotSubSystemConfig {
        return super.getRobotSubSystemConfig().apply {
            useOpenCV = true
        }
    }

//    override fun getOpenCVConfig(): MOEOpenCVConfig {
//        return super.getOpenCVConfig().apply { enablePreview   }
//    }
}