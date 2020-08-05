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
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$embeddedKotlinVersion")
    }

}
extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

subprojects {
    repositories {
        mavenLocal()
        flatDir {
            dirs("../libs")
        }
        google()
        jcenter()

    }
}
