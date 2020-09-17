package org.firstinspires.ftc.teamcode.drive;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.util.Encoder;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
@Config
public class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 0;
    public static double WHEEL_RADIUS = 2; // in
    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed

    public static double LATERAL_DISTANCE = 10; // in; distance between the left and right wheels
    public static double FORWARD_OFFSET = 4; // in; offset of the lateral wheel

    private Encoder leftEncoder, rightEncoder, frontEncoder;

    public StandardTrackingWheelLocalizer(HardwareMap hardwareMap) {
        super(Arrays.asList(
                new Pose2d(-0.343, 7.835), // left parallel
                new Pose2d(-0.343, -7.835), // right parallel
                new Pose2d(-0.276, 0.343, Math.toRadians(90.0))
        ));

        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "BRDM13"));
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "FRDM12"));
        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "BLDM11"));

        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    double RIGHT_SCALAR = 306.3309693;
    double LEFT_SCALAR = -307.3191489;
    double STRAFE_SCALAR = -305.1867612;


    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
                leftEncoder.motor.getCurrentPosition() / LEFT_SCALAR,
                rightEncoder.motor.getCurrentPosition() / RIGHT_SCALAR,
                frontEncoder.motor.getCurrentPosition() / STRAFE_SCALAR
//                encoderTicksToInches(leftEncoder.getCurrentPosition()),
//                encoderTicksToInches(rightEncoder.getCurrentPosition()),
//                encoderTicksToInches(frontEncoder.getCurrentPosition())
        );
    }

    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        // TODO: If your encoder velocity can exceed 32767 counts / second (such as the REV Through Bore and other
        //  competing magnetic encoders), change Encoder.getRawVelocity() to Encoder.getCorrectedVelocity() to enable a
        //  compensation method

        return Arrays.asList(
                leftEncoder.motor.getVelocity() / LEFT_SCALAR,
                rightEncoder.motor.getVelocity() / RIGHT_SCALAR,
                frontEncoder.motor.getVelocity() / STRAFE_SCALAR
        );
    }
}
