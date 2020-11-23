import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    id("com.android.library")
}

android {
    defaultConfig {
        minSdkVersion(25)
        targetSdkVersion(30)
        buildToolsVersion("30.0.2")
    }
    compileSdkVersion(30)
//this.


    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }

}


dependencies {

    implementation("org.firstinspires.ftc:Inspection:6.0.1")
    implementation("org.firstinspires.ftc:Blocks:6.0.1")
    implementation("org.firstinspires.ftc:RobotCore:6.0.1")
    implementation("org.firstinspires.ftc:RobotServer:6.0.1")
    implementation("org.firstinspires.ftc:OnBotJava:6.0.1")
    implementation("org.firstinspires.ftc:Hardware:6.0.1")
    implementation("org.firstinspires.ftc:FtcCommon:6.0.1")
    implementation("com.acmerobotics.dashboard:dashboard:0.3.9")
    implementation(name = "WirelessP2p-release", ext = "aar", group = "")
    implementation("androidx.appcompat:appcompat:1.2.0")
}

