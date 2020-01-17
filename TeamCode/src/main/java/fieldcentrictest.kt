import org.firstinspires.ftc.teamcode.constants.ReferenceHolder
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.Point
import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.toRadians
import kotlin.math.cos
import kotlin.math.sin

var prevPose = Point(0.0,0.0)
var pose = Point(0.0,0.0)
private fun getDeltaPose(currPose: Point, degAngle: Double): Point {
    val radAngle = degAngle.toRadians()
    val deltaPose = currPose - prevPose
    prevPose = currPose
    return Point((deltaPose.x) * cos(radAngle) + (deltaPose.y) * sin(radAngle),
            -(deltaPose.x) * sin(radAngle) + (deltaPose.y) * cos(radAngle))
}
fun main() {

    while(true){
        val x = readLine()?.toDouble()!!
        val y = readLine()?.toDouble()!!
        val angle = readLine()?.toDouble()!!
        val deltaPose = getDeltaPose(Point(x,y),angle)
        pose += deltaPose
        println("final pose: " + pose)
        println("angle: " + angle)
    }
}