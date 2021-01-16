import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    id("com.android.application")
    kotlin("android")
//    id("org.moeftc.hotcode") version "1.9"
}

android {
    aaptOptions.noCompress("tflite")
    defaultConfig {
        applicationId = "com.qualcomm.ftcrobotcontroller"
//        resConfigs("en", "xxhdpi")
        compileSdkVersion(30)
        buildToolsVersion("30.0.3")
        minSdkVersion(25)
        targetSdkVersion(30)
        versionCode = 39
        versionName = "6.1"

    }
    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
    buildTypes["debug"].apply {
        isDebuggable = true
        isJniDebuggable = true
        isRenderscriptDebuggable = true
        ndk {
            abiFilters.add("armeabi-v7a")
        }
    }
    sourceSets["main"].apply {
        jniLibs.srcDir("../libs")
    }

}

dependencies {
    implementation(project(":FtcRobotController"))
    implementation("org.firstinspires.ftc:RobotCore:6.1.1")
    implementation("org.firstinspires.ftc:Hardware:6.1.1")
    implementation("org.firstinspires.ftc:FtcCommon:6.1.1")
    implementation(name = "tfod-release", ext = "aar", group = "")
    implementation(name = "tensorflow-lite-0.0.0-nightly", ext = "aar", group = "")
    implementation(name = "t265lib-release", ext = "aar", group = "")
    implementation(kotlin("stdlib-jdk8","1.4.20"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
    implementation("org.openftc:easyopencv:1.4.1")
    compileOnly("org.processing:core:3.3.7")
    compileOnly("org.apache.commons:commons-math3:3.6.1")
    compileOnly("com.acmerobotics.dashboard:dashboard:0.3.10")
    compileOnly("com.acmerobotics.roadrunner:core:0.5.2")

}
