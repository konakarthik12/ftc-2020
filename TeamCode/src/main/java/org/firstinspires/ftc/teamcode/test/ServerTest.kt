package org.firstinspires.ftc.teamcode.test

//import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.opmodeutils.MOEGamePad.Button
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.robotcontroller.moeglobal.server.MOESocketHandler.moeWebServer
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOELoopedOpMode
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.utilities.external.toFixed
import org.firstinspires.ftc.teamcode.utilities.internal.addData
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.random.Random

@TeleOp(name = "ServerTest")
class ServerTest : MOELoopedOpMode(), SensorEventListener {


    override fun initOpMode() {
        sensorManager = hardwareMap.appContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR)
        addListeners()
    }

    val timer = ElapsedTime()
    override fun afterInit() {
        super.afterInit()
        timer.reset()
    }

    val wait = 100
    val data = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN)

    override fun loop() {
        while (timer.milliseconds() < wait) {
            return
        }
        timer.reset()
        getData().forEachIndexed { index, it ->
            data.putFloat(index * 4, it)
        }


//        moeWebServer.broadcast(getData())
        moeWebServer.broadcast(data)
    }

    fun addListeners() {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                    this,
                    accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_UI
            )
        }
        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also { magneticField ->
            sensorManager.registerListener(
                    this,
                    magneticField,
                    SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_UI
            )
        }
    }

    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    private fun getData(): FloatArray {
        SensorManager.getRotationMatrix(
                rotationMatrix,
                null,
                accelerometerReading,
                magnetometerReading
        )


        SensorManager.getOrientation(rotationMatrix, orientationAngles)
        return orientationAngles
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.size)
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, magnetometerReading, 0, magnetometerReading.size)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

}