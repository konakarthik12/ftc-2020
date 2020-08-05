import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    id("com.android.library")
}
android {
    defaultConfig {
        minSdkVersion(25)
        targetSdkVersion(29)
        compileSdkVersion(29)
        buildToolsVersion("29.0.3")
    }

    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
}


dependencies {
    aar("Inspection-release")
    aar("Blocks-release")
    aar("RobotCore-release")
    aar("RobotServer-release")
    aar("OnBotJava-release")
    aar("Hardware-release")
    aar("FtcCommon-release")
    aar("WirelessP2p-release")
    implementation("com.android.support:support-compat:28.0.0")

}

fun DependencyHandlerScope.aar(name: String) = implementation("", name, ext = "aar")