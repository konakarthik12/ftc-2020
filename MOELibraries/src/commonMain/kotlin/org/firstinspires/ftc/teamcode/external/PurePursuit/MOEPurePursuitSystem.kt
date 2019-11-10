package org.firstinspires.ftc.teamcode.utilities.PurePursuit

import org.firstinspires.ftc.teamcode.external.PurePursuit.MOEPurePursuitPath


class MOEPurePursuitSystem(points: List<PurePursuitPoint>, private val options: MOEPurePursuitOptions) {
    private var path: MOEPurePursuitPath = MOEPurePursuitPath(points, options)
    private var lastLeftVelocity: Double = 0.0
    private var lastRightVelocity: Double = 0.0

    fun getWheelVelocities(currentPosition: PurePursuitPoint,
                           currentHeading: Int,
                           leftActualVelocity: Int,
                           rightActualVelocity: Int): Pair<Double, Double> {
        var lastKnownPointIndex = 0
        var closestPoint: PurePursuitPoint

        lastKnownPointIndex = path.getClosestPointIndex(lastKnownPointIndex, currentPosition)
        closestPoint = path[lastKnownPointIndex]

        val heading = currentHeading

        val lookaheadPoint = path.getLookaheadPointFromPathing(lastKnownPointIndex, currentPosition)

        val curvature = getSignedCurvatureFromLookaheadPoint(
                lookaheadPoint,
                currentPosition,
                heading.toDouble(),
                options.lookAheadDistance
        )

        val leftTV = getLeftWheelTargetVelocity(closestPoint.velocity, curvature)
        val rightTV = getRightWheelTargetVelocity(closestPoint.velocity, curvature)

        val leftTA = 0.0 //leftTV - lastLeftVelocity
        val rightTA = 0.0 //rightTV - lastRightVelocity

        lastLeftVelocity = leftTV
        lastRightVelocity = rightTV

        val leftFeedforward = (options.K_V * leftTV) + (options.K_A * leftTA)
        val rightFeedforward = (options.K_V * rightTV) + (options.K_A * rightTA)

        //            leftWheelTargetVelocity = normalizeVelocity(leftWheelTargetVelocity);
        //            rightWheelTargetVelocity = normalizeVelocity(rightWheelTargetVelocity);
        //            double[] velocities = normalizeVelocities(leftWheelTargetVelocity, rightWheelTargetVelocity);
        //            leftWheelTargetVelocity = velocities[0];
        //            rightWheelTargetVelocity = velocities[1];
        //robot.chassis.setVelocities(leftWheelTargetVelocity,rightWheelTargetVelocity);
        //TODO: if (!path.isForward()) {
        //            leftTargetVel = -leftTargetVel;
        //            rightTargetVel = -rightTargetVel;

        //            double leftFeedback = getWheelFeedbackVelocity(leftWheelTargetVelocity,
        //                                                           robot.chassis.getAStarVelocity(robot.chassis.frontLeftMotor));
        //            double rightFeedback = getWheelFeedbackVelocity(rightWheelTargetVelocity,
        //                                                            robot.chassis.getAStarVelocity(robot.chassis.frontRightMotor));

        val leftFeedback = options.K_P * (leftTV - leftActualVelocity)
        val rightFeedback =options.K_P * (rightTV - rightActualVelocity)

        val leftFinalVelocity = leftFeedforward + leftFeedback
        val rightFinalVelocity = rightFeedforward + rightFeedback
//TODO: Fins
        val scaleDown = 0.4

        return Pair(leftFinalVelocity, rightFinalVelocity)
    }

    private fun getLeftWheelTargetVelocity(targetVelocity: Double, curvature: Double): Double {
        return targetVelocity * (2 + curvature * options.track_width) / 2
    }

    private fun getRightWheelTargetVelocity(targetVelocity: Double, curvature: Double): Double {
        return targetVelocity * (2 - curvature * options.track_width) / 2
    }
}



