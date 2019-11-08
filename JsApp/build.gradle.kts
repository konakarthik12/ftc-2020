import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("kotlin2js")
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation(project(":MOELibraries"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-js")
    testImplementation("org.jetbrains.kotlin:kotlin-test-nodejs-runner")
}

tasks {
    compileKotlin2Js {
        kotlinOptions {
            moduleKind = "commonjs"
//            outputFile = "node/index.js"
            sourceMap = true
        }
    }
//    compileKotlin2Js {
//        kotlinOptions {
//            outputFile = "${sourceSets.main.get().output.resourcesDir}/output.js"
//            sourceMap = true
//        }
//    }
    val unpackKotlinJsStdlib by registering {
        group = "build"
        description = "Unpack the Kotlin JavaScript standard library"
        val outputDir = file("$buildDir/$name")
        inputs.property("compileClasspath", configurations.compileClasspath.get())
        outputs.dir(outputDir)
        doLast {
            val kotlinStdLibJar = configurations.compileClasspath.get().single {
                it.name.matches(Regex("kotlin-stdlib-js-.+\\.jar"))
            }
            copy {
                includeEmptyDirs = false
                from(zipTree(kotlinStdLibJar))
                into(outputDir)
                include("**/*.js")
                exclude("META-INF/**")
            }
        }
    }
      val pullLibs by registering(Copy::class) {
        group = "build"

        includeEmptyDirs = false
      val libPath = rootProject.buildDir.toString()+"/js/packages/MOETime-MOELibraries/kotlin"
        from(libPath){
         exclude("**/*.kjsm")
        }
        into("$buildDir/web/node_modules")
    }
    val assembleWeb by registering(Copy::class) {
        group = "build"
        description = "Assemble the web application"
        includeEmptyDirs = false
//        val libPath = rootProject.buildDir.toString()+"/js/packages/MOETime-MOELibraries")
     //   println(project.file(package.json))
        //from()
        from(unpackKotlinJsStdlib)
        from(sourceSets.main.get().output) {
            exclude("**/*.kjsm")
        }
        into("$buildDir/web")
    }
    assembleWeb {
        dependsOn(pullLibs)
    }
    assemble {
        dependsOn(assembleWeb)
    }
    val npmInit by registering(Exec::class) {
        commandLine("cmd","/c","npm", "init", "-y")
    }

    val npmInstall by registering(Exec::class) {
        commandLine("cmd","/c","npm", "install", "kotlin", "express", "--save")
    }

    val npmRun by registering(Exec::class) {
   

        commandLine("node", "build/web/JsApp.js")
    }

    npmRun.dependsOn(assemble)
}
