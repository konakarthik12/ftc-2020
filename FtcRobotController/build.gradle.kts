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
    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }

}


dependencies {
    val version = "6.1.1"
    implementation("org.firstinspires.ftc:Inspection:$version")
    implementation("org.firstinspires.ftc:Blocks:$version")
    implementation("org.firstinspires.ftc:RobotCore:$version")
    implementation("org.firstinspires.ftc:RobotServer:$version")
    implementation("org.firstinspires.ftc:OnBotJava:$version")
    implementation("org.firstinspires.ftc:Hardware:$version")
    implementation("org.firstinspires.ftc:FtcCommon:$version")
    implementation("com.acmerobotics.dashboard:dashboard:0.3.9")
    implementation("androidx.appcompat:appcompat:1.2.0")
}

