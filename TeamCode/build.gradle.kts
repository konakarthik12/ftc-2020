import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    id("com.android.application")
    kotlin("android")
    id("org.moeftc.hotcode") version "1.9"
}

android {
    aaptOptions.noCompress("tflite")
    defaultConfig {
        applicationId = "com.qualcomm.ftcrobotcontroller"
        resConfigs("en", "xxhdpi")
        compileSdkVersion(30)
        buildToolsVersion("30.0.2")
        minSdkVersion(25)
        targetSdkVersion(30)
        versionCode = 38
        versionName = "6.0"
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
            abiFilters.add(
                    "armeabi-v7a"
            )
        }
    }
    sourceSets["main"].apply {
        jniLibs.srcDir("../libs")
    }

}

dependencies {
    implementation(project(":FtcRobotController"))
    implementation("org.firstinspires.ftc:RobotCore:6.0.1")
    implementation("org.firstinspires.ftc:Hardware:6.0.1")
    implementation("org.firstinspires.ftc:FtcCommon:6.0.1")
    implementation(name = "WirelessP2p-release", ext = "aar", group = "")
    implementation(name = "tfod-release", ext = "aar", group = "")
    implementation(name = "tensorflow-lite-0.0.0-nightly", ext = "aar", group = "")
    embeddedKotlin("stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
    compileOnly("org.openftc:easyopencv:1.3.2")
    compileOnly("org.processing", "core", "3.3.7")
    compileOnly("org.apache.commons:commons-math3:3.6.1")
    compileOnly("com.acmerobotics.dashboard:dashboard:0.3.9")
    compileOnly("com.acmerobotics.roadrunner:core:0.5.2")

}
