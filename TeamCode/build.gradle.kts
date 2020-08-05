plugins {
    id("com.android.application")
    kotlin("android")
    id("org.moeftc.fastcode") version "1.4"
}
fastcode {
    language = "kotlin"
    minApiLevel = 25
}
android {
    aaptOptions.noCompress("tflite")

    defaultConfig {
        applicationId = "com.qualcomm.ftcrobotcontroller"
        resConfigs("en", "xxhdpi")

        compileSdkVersion(29)
        buildToolsVersion("30.0.1")
        minSdkVersion(25)
        targetSdkVersion(29)
        versionCode = 36
        versionName = "5.4"

//        val manifestFile = project(":FtcRobotController").file("src/main/AndroidManifest.xml")
//        val manifestText = manifestFile.readText()
//        val code = manifestText.substringAfter("versionCode=\"").substringBefore("\"").toInt()
//        val name = manifestText.substringAfter("versionName=\"").substringBefore("\"")
//
//        println(code)
//        println(name)

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes["debug"].apply {
        isDebuggable = true
        isJniDebuggable = true
        isRenderscriptDebuggable = true
        ndk {
            abiFilters(
//                    "armeabi-v7a",
                    "arm64-v8a"
            )
        }
    }

    sourceSets["main"].apply {
        jniLibs.srcDir("../libs")
    }
}


dependencies {
    implementation(project(":FtcRobotController"))
    aar("RobotCore-release")
    aar("RobotServer-release")
    aar("Hardware-release")
    aar("FtcCommon-release")
    aar("WirelessP2p-release")
    aar("tfod-release")
    aar("tensorflow-lite-0.0.0-nightly")
//
    embeddedKotlin("stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
    compileOnly("org.openftc:easyopencv:1.3.2")
}

fun DependencyHandlerScope.aar(name: String) = implementation("", name, ext = "aar")
