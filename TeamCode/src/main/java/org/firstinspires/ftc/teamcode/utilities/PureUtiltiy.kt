package org.firstinspires.ftc.teamcode.utilities

import android.graphics.Bitmap
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.utilities.AdvancedMath.Rectangle
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


fun Telemetry.addData(data: Any) {
    this.addData(data.toString(), "")
    //    this.update()
}

operator fun DatabaseReference.get(child: String): DatabaseReference {
    return this.child(child);
}

fun Bitmap.crop(frame: Rectangle): Bitmap {
    return Bitmap.createBitmap(this, frame.x, frame.y, frame.width, frame.height)
}

fun Bitmap.saveTo(file: String) {
    saveTo(File(file))
}

fun DatabaseReference.delete() {
    this.setValue(null)
}

fun DatabaseReference.pushData(function: () -> Any?) {
    this.push().setValue(function())
}

fun Bitmap.saveTo(file: File) {
    try {
        FileOutputStream(file).use { out ->
            this.compress(Bitmap.CompressFormat.PNG, 100, out) // bmp is your Bitmap instance
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

}

fun Double.toFixed(digits: Int): String = "%.${digits}f".format(this)

fun Bitmap.scale(width: Int, height: Int): Bitmap? =
        Bitmap.createScaledBitmap(this, width, height, false)

operator fun DataSnapshot.get(child: String): DataSnapshot {
    return child(child)
}

//operator fun DataSnapshot.get(s: String): Rectangle {
//
//}

