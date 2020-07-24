plugins {
    id("com.android.application")
    kotlin("android")
    id("org.moeftc.fastcode") version "1.0"
}
android {
    aaptOptions.noCompress("tflite")

    defaultConfig {
        applicationId = "com.qualcomm.ftcrobotcontroller"
        compileSdkVersion(30)
        buildToolsVersion("30.0.1")
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 36
        versionName = "5.4"

////        def manifestFile = project(":FtcRobotController").file("src/main/AndroidManifest.xml")
////        def manifestText = manifestFile.getText()
////        //
////        def vCodePattern = Pattern.compile("versionCode=\"(\\d+(\\.\\d+)*)\"")
////        def matcher = vCodePattern.matcher(manifestText)
////        matcher.find()
////        def vCode = Integer.parseInt(matcher.group(1))
////        //
////        def vNamePattern = Pattern.compile("versionName=\"(.*)\"")
////        matcher = vNamePattern.matcher(manifestText)
////        matcher.find()
////        def vName = matcher.group(1)
//        //
////        System.out.println(vCode)
////        System.out.println(vName)

    }
    buildTypes["debug"].apply {
        isDebuggable = true
        isJniDebuggable = true
        isRenderscriptDebuggable = true
        ndk {
            abiFilters(
                    "armeabi-v7a"
//                    , "arm64-v8a"
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

    embeddedKotlin("stdlib-jdk7")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
    compileOnly("org.openftc:easyopencv:1.3.2")
    implementation("com.github.greenpizza1203:fast-code-service:v1.0.0")

}
fun DependencyHandlerScope.aar(name: String) = implementation("", name, ext = "aar")
