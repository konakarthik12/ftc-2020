import kotlin.math.PI
import kotlin.math.asin
import kotlin.math.atan2

fun getYawFromQuaternion(param: DoubleArray): Double {
    val (w, y, z, x) = param
//    val pitch = asin(2.0 * (x * z - w * y)) * 180.0 / PI
//    val roll = atan2(2.0 * (w * x + y * z), w * w - x * x - y * y + z * z) * 180.0 / PI
    val yaw = atan2(2.0 * (w * z + x * y), w * w + x * x - y * y - z * z) * 180.0 / PI
    return yaw
}