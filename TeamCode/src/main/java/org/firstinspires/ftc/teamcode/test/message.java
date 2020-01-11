//import java.util.Arrays;
//
//
//public class MyClass {
//        //constants (set these values in code)
//        static double robotInitialPose[] = {0, 0, 0}; //where the center of the robot is starting on the field (x,y,theta) 0 degrees = up, positive = cw
//        static double cameraRelativePose[] = {0, 0, 0};//first two values are the polar offsets from the center of the robot relative to the robot, third value is the rotation of the camera relative to the front of the robot
//
//        //initializing array (not set in code, calculated later) (x, y, theta)
//        static double cameraInitialPose[] = {0, 0, 0}; //where the center of the camera starts on the field
//
//        //initilzing array (these are the raw values from the SLAM camera) (x, y, theta)
//       static  double rawPose[] = {0, 10, 0.0};
//
//        //initializing array (not set in code, calculated later)
//         static double cameraPose[] = {0.0, 0.0, 0.0}; //the actual camera position (x,y,theta)
//
//        //initializing array (not set in code, calculated later)
//        static  double robotPose[] = {0.0, 0.0, 0.0}; //the actual robot position (x,y,theta)
//
//
//
//    public static void main(String args[]) {
//
//
//        findCameraInitialPose(robotInitialPose[0], robotInitialPose[1], robotInitialPose[2],       cameraRelativePose[0],cameraRelativePose[1],cameraRelativePose[2] ); //takes in 6 set constants (robot init pose and camera pose relateive to robot)
//        findCameraPose(cameraInitialPose[0],cameraInitialPose[1],cameraInitialPose[2],     rawPose[0],rawPose[1],rawPose[2]); //takes in 3 calculated constants, and 3 values (camera init pose and values from SLAM cam)
//        findRobotPose(cameraPose[0],cameraPose[1],cameraPose[2], cameraRelativePose[0],cameraRelativePose[1],cameraRelativePose[2] ); //takes in 3 calculated values and 3 constants (camera pose and camera pose relative to robot)
//
//
//      System.out.println("Camera Initial Pose: " +  Arrays.toString(cameraInitialPose));
//      System.out.println("Raw Pose: " +  Arrays.toString(rawPose));
//      System.out.println("Camera pose: " + Arrays.toString(cameraPose));
//      System.out.println("Robot Pose: " + Arrays.toString(robotPose));
//    }
//
//
//    public static void findCameraInitialPose(double robotInitialX, double robotInitialY, double robotInitialAngle, double cameraRelativeR,double cameraRelativeTheta,double cameraRelativeAngle){
//        //conversion from robot initial to camera initial so we can plot how the robot starts in the beginning of the auton (instead of the camera
//
//        cameraInitialPose[0] = robotInitialX + cameraRelativeR*Math.sin(Math.toRadians(robotInitialAngle + cameraRelativeTheta));
//        cameraInitialPose[1] = robotInitialY + cameraRelativeR*Math.cos(Math.toRadians(robotInitialAngle + cameraRelativeTheta));
//        cameraInitialPose[2] = robotInitialAngle + cameraRelativeAngle;
//
//    }
//    public static void findCameraPose(double cameraInitialX, double cameraInitialY, double cameraInitialAngle, double cameraRawX, double cameraRawY, double cameraRawAngle){
//        //uses camera initial + camera raw values to calculate camera pose
//        double r = Math.sqrt(cameraRawX*cameraRawX + cameraRawY*cameraRawY);
//         double theta = Math.atan2(cameraRawX,cameraRawY) + Math.toRadians(cameraInitialAngle);
//
//         cameraPose[0] = cameraInitialX + r*Math.sin(theta);
//         cameraPose[1] = cameraInitialY + r*Math.cos(theta);
//         cameraPose[2] = cameraInitialAngle + cameraRawAngle;
//
//    }
//    public static void findRobotPose(double cameraX, double cameraY, double cameraAngle, double cameraRelativeR,double cameraRelativeTheta,double cameraRelativeAngle){
//        //uses camera Pose + cameraRelative pose to calcculate robot pose
//        robotPose[0] = cameraX + cameraRelativeR*Math.sin(Math.toRadians(cameraAngle + cameraRelativeTheta));
//        robotPose[1] = cameraY + cameraRelativeR*Math.cos(Math.toRadians(cameraAngle + cameraRelativeTheta));
//        robotPose[2] = cameraAngle - cameraRelativeAngle;
//    }
//}
