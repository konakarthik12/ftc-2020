import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    id("com.android.library")
}
android {
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        compileSdkVersion(30)
        buildToolsVersion("30.0.1")
    }

    compileOptions {
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
}


dependencies {
    implementation("com.android.support:support-compat:28.0.0")
    implementation("com.github.greenpizza1203:fast-code-service:0c00c4c467")
    aar("Inspection-release")
    aar("Blocks-release")
    aar("RobotCore-release")
    aar("RobotServer-release")
    aar("OnBotJava-release")
    aar("Hardware-release")
    aar("FtcCommon-release")
    aar("WirelessP2p-release")

}

fun DependencyHandlerScope.aar(name: String) = implementation("", name, ext = "aar")