package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.appContext
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.hardwareMap
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation

data class MOEOpenCVConfig(var enablePreview: Boolean = true, val drawOverlay: Boolean = true)