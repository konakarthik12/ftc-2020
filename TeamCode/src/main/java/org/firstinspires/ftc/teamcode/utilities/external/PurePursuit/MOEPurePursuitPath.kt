package org.firstinspires.ftc.teamcode.utilities.external.PurePursuit


import org.firstinspires.ftc.teamcode.utilities.external.AdvancedMath.*
import org.firstinspires.ftc.teamcode.utilities.external.clone
import org.firstinspires.ftc.teamcode.utilities.external.coerceIn
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt


class MOEPurePursuitPath(var points: List<PurePursuitPoint>, private val options: MOEPurePursuitOptions) {
    //    constructor(originalPoints: List<Point>, options: MOEPurePursuitOptions) :
    //            this(originalPoints.map { it.toPurePursuitPoint() }, options)

    //TODO: add astar

    //    constructor(srcX: Double, srcY: Double, destX: Double, destY: Double, options: MOEPurePursuitOptions) :
    //            this(mutableListOf<>(PurePursuitPoint(srcX, srcY), PurePursuitPoint(destX, destY)), options)
    val originalPoints = points
    lateinit var injectedPoints: MutableList<PurePursuitPoint>
    private lateinit var smoothedPoints: List<PurePursuitPoint>

    init {
        injectPoints()
        smoothPoints()
        //                setMaxVelocities()
        //                smoothVelocities()
        points.forEach { it.velocity = options.overallMaxVelocity }
    }

    private fun injectPoints() {
        //        val newPathing = ArrayList<PurePursuitPoint>()
        var segments = points.toLineSegmentList()
        val injectedMulti = segments.map { it.injectPoints(options.spacing) }
        injectedMulti.forEach { it.first().isCriticalPoint = true }
        injectedPoints = injectedMulti.flatten().toMutableList()
        points.last().apply {
            isCriticalPoint = true
            injectedPoints.add(this)
        }
        //        lastPoint.isCriticalPoint = true
        //        injectedMulti.add(lastPoint)


        //        this.injectedPoints = injectedMulti
        this.points = injectedPoints
    }

    private fun smoothPoints() {
        smoothedPoints = points.clone()
        var change = options.tolerance
        while (change >= options.tolerance) {
            change = 0.0
            for (i in 1 until smoothedPoints.size - 1) repeat(2) { j ->
                val aux = smoothedPoints[i][j]
                smoothedPoints[i][j] += options.smoothingA * (smoothedPoints[i][j] - smoothedPoints[i][j]) +
                        options.smoothingB * (smoothedPoints[i - 1][j] + smoothedPoints[i + 1][j] - 2.0 * smoothedPoints[i][j])
                change += abs(aux - smoothedPoints[i][j])
            }
        }
        points = smoothedPoints
    }

    private fun setMaxVelocities() {
        for (i in 1 until points.size - 1) {
            val curvature = PurePursuitPoint.getCurvatureOfPoints(points[i - 1], points[i], points[i + 1])
            //            println("curvature; $curvature")
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

        val searchIndexes = ((lastKnownPointIndex - options.lookBack)..(lastKnownPointIndex + options.lookForward)).coerceIn(points.indices)

        return points.slice(searchIndexes).minBy { it.distanceFrom(currentPoint) }?.let { points.indexOf(it) }!!
        //        for (i in (lastKnownPointIndex - options.lookBack)..lastKnownPointIndex + options.lookForward) {
        //            if (i >= 0 && i < points.size) {
        //                val dist = points[i].distanceFrom(currentPoint)
        //                if (dist < closestDistance) {
        //                    closestDistance = dist
        //                    closestPointIndex = i
        //                }
        //            }


    }


    fun getLookaheadPoint(
            startPoint: PurePursuitPoint,
            endPoint: PurePursuitPoint,
            currentPosition: PurePursuitPoint,
            lookaheadDistance: Double,
            onLastSegment: Boolean
    ): PurePursuitPoint? {
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

    fun getLookaheadPointFromPathing(closestPointIndex: Int, currentPosition: PurePursuitPoint): PurePursuitPoint {
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
    val size
        get() = points.size

    //    fun getSize() = points.size
}





