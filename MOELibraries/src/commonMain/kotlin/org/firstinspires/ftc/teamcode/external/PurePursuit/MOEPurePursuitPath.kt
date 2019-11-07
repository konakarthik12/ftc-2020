package org.firstinspires.ftc.teamcode.utilities.PurePursuit


import org.firstinspires.ftc.teamcode.external.AdvancedMath.getCircleLineIntersection
import org.firstinspires.ftc.teamcode.external.PurePursuit.PurePursuitVector
import org.firstinspires.ftc.teamcode.utilities.Point
import kotlin.math.*


class MOEPurePursuitPath(var points: List<PurePursuitPoint>, private val options: MOEPurePursuitOptions) {

    init {
        injectPoints()
        smoothPoints()
        setMaxVelocities()
        smoothVelocities()
    }

    private fun injectPoints() {
        val newPathing = ArrayList<PurePursuitPoint>()

        for (i in 0 until points.size - 1) {
            var vector = PurePursuitVector(points[i + 1] - points[i])

            val numberOfPointsThatFit = ceil(vector.magnitude / options.spacing).toInt()
            vector.normalize()
            vector *= options.spacing

            val originalPoint = PurePursuitPoint(points[i].x, points[i].y)
            originalPoint.isCriticalPoint = true
            newPathing.add(originalPoint)

            for (a in 1 until numberOfPointsThatFit) {
                newPathing.add(
                        PurePursuitPoint(
                                points[i].x + vector.x * a,
                                points[i].y + vector.y * a
                        )
                )
            }
        }

        val lastPoint = points[points.size - 1]
        lastPoint.isCriticalPoint = true
        newPathing.add(lastPoint)
        this.points = newPathing
    }

    private fun smoothPoints() {
        val newPath = ArrayList<PurePursuitPoint>(points.size)

        for (foo in points) {
            newPath.add(PurePursuitPoint(foo.x, foo.y))
        }

        var change = options.tolerance
        while (change >= options.tolerance) {
            change = 0.0
            for (i in 1 until points.size - 1) {
                var aux = newPath[i].x
                newPath[i].x += options.smoothingA * (points[i].x - newPath[i].x) + options.smoothingB * (newPath[i - 1].x + newPath[i + 1].x - 2.0 * newPath[i].x)
                change += abs(aux - newPath[i].x)

                aux = newPath[i].y
                newPath[i].y += options.smoothingA * (points[i].y - newPath[i].y) + options.smoothingB * (newPath[i - 1].y + newPath[i + 1].y - 2.0 * newPath[i].y)
                change += abs(aux - newPath[i].y)
            }
        }
        points = newPath
    }

    private fun setMaxVelocities() {
        for (i in 1 until points.size - 1) {
            val curvature = PurePursuitPoint.getCurvatureOfPoints(points[i - 1], points[i], points[i + 1])
            println("curvature; $curvature")
            points[i].velocity = min(options.overallMaxVelocity, options.turningConstant / curvature)
        }
        points[0].velocity = options.overallMaxVelocity
        points[points.size - 1].velocity = 0.0
    }

    private fun smoothVelocities() {
        var velocity: Double
        points[points.size - 1].velocity = 0.0
        for (i in points.size - 2 downTo 0) {
            val nextVelocity = points[i + 1].velocity
            val a = options.overallMaxVelocity
            val distance = points[i].distanceFrom(points[i + 1])
            val newVelocity = sqrt(nextVelocity.pow(2.0) + 2.0 * a * distance)
            velocity = min(points[i].velocity, newVelocity)
            points[i].velocity = velocity
        }
    }

    fun getClosestPointIndex(lastKnownPointIndex: Int, currentPoint: PurePursuitPoint): Int {
        var closestPointIndex = 0
        var closestDistance = Double.MAX_VALUE

        for (i in lastKnownPointIndex - options.lookBack until lastKnownPointIndex + options.lookForward + 1) {
            if (i >= 0 && i < points.size) {
                val dist = points[i].distanceFrom(currentPoint)
                if (dist < closestDistance) {
                    closestDistance = dist
                    closestPointIndex = i
                }
            }
        }
        return closestPointIndex
    }


    fun getLookaheadPoint(
            startPoint: PurePursuitPoint,
            endPoint: PurePursuitPoint,
            currentPosition: PurePursuitPoint,
            lookaheadDistance: Double,
            onLastSegment: Boolean
    ): Point? {
        val progress = getCircleLineIntersection(startPoint, endPoint, currentPosition, lookaheadDistance)
        if (progress.isNaN()) {
            return null
        }
        if (onLastSegment) {
            //TODO: add last segment code
        }
        val intersectVector = endPoint.minus(startPoint)
        val vectorSegment = intersectVector.times(progress)
        return startPoint.plus(vectorSegment)
    }

    fun getLookaheadPointFromPathing(closestPointIndex: Int, currentPosition: PurePursuitPoint): Point {
        for (i in closestPointIndex until points.size - 1) {
            val a = points[i]
            val b = points[i + 1]

            val lp = getLookaheadPoint(a, b, currentPosition, options.lookAheadDistance, false)

            if (lp != null) {
                return lp
            }
        }
        return points[closestPointIndex]
    }

    operator fun get(index: Int): PurePursuitPoint = points[index]
    fun getSize() = points.size
}