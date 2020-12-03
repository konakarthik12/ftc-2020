/**
 * Top-level command file for ftc_app project.
 *
 * It is extraordinarily rare that you will ever need to edit this file.
 */
//plugins {
//    idea
//}
buildscript {
//    var kotlin_version: String by extra
//    kotlin_version = "1.3.72"
    repositories {
        google()
        jcenter()

    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
//        println(org.gradle.kotlin.dsl.embeddedKotlinVersion)
        classpath(embeddedKotlin("gradle-plugin"))
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
task("clean", Delete::class) {
    this.delete(rootProject.buildDir)
}
//idea {
//    module {
////        excludeDirs = excludeDirs + (file("build"))
//        excludeDirs = excludeDirs + file("build")
//    }
//}