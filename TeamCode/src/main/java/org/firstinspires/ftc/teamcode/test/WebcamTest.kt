package org.firstinspires.ftc.teamcode.test

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPenCV.MOEPipeline
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.openftc.easyopencv.OpenCvCameraRotation

/*
 * Copyright (c) 2019 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */



@TeleOp
class WebcamExample : MOETeleOp() {
    lateinit var webcam: OpenCvCamera
    override fun initOpMode() {
        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.packageName)
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName::class.java, "Webcam 1"), cameraMonitorViewId)
//        webcam.setPipeline(MOEPipeline())
        webcam.openCameraDevice()
        webcam.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT)

    }

    override fun mainLoop() {
        telemetry.addData("Frame Count", webcam.frameCount)
        telemetry.addData("FPS", String.format("%.2f", webcam.fps))
        telemetry.addData("Total frame time ms", webcam.totalFrameTimeMs)
        telemetry.addData("Pipeline time ms", webcam.pipelineTimeMs)
        telemetry.addData("Overhead time ms", webcam.overheadTimeMs)
        telemetry.addData("Theoretical max FPS", webcam.currentPipelineMaxFps)
        telemetry.update()
        /*
             * NOTE: stopping the stream from the camera early (before the end of the OpMode
             * when it will be automatically stopped for you) *IS* supported. The "if" statement
             * below will stop streaming from the camera when the "A" button on gamepad 1 is pressed.
             */when {
            gamepad1.a -> {
                webcam.stopStreaming()
            }
            gamepad1.x -> {
                webcam.pauseViewport()
            }
            gamepad1.y -> {

                webcam.resumeViewport()
            }
            /*
                 * For the purposes of this sample, throttle ourselves to 10Hz loop to avoid burning
                 * excess CPU cycles for no reason. (By default, telemetry is only sent to the DS at 4Hz
                 * anyway). Of course in a real OpMode you will likely not want to do this.
                 */
        }
    }

    override fun stop() {
        super.stop()
        webcam.stopStreaming()

    }
}