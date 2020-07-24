/**
 * Top-level command file for ftc_app project.
 *
 * It is extraordinarily rare that you will ever need to edit this file.
 */
buildscript {
    repositories {
        google()
        jcenter()

    }
    dependencies {
        //TODO: waiting for intellij
        classpath("com.android.tools.build:gradle:3.6.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$embeddedKotlinVersion")
    }

}
extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

subprojects {
    repositories {
        flatDir {
            dirs("../libs")
        }
        mavenLocal()
        google()
        jcenter()
    }
}
