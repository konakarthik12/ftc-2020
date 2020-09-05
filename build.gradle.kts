/**
 * Top-level command file for ftc_app project.
 *
 * It is extraordinarily rare that you will ever need to edit this file.
 */

buildscript {
//    var kotlin_version: String by extra
//    kotlin_version = "1.3.72"
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
//        println(org.gradle.kotlin.dsl.embeddedKotlinVersion)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$embeddedKotlinVersion")
//        classpath(kotlinModule("gradle-plugin", kotlin_version))
    }

}
extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

subprojects {
    repositories {
        mavenLocal()
        maven("https://dl.bintray.com/first-tech-challenge/ftcsdk/")
        flatDir {
            dirs("../libs")
        }
        google()
        jcenter()

    }
}
