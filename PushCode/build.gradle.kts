//import com.pushy.tasks.compileStuff
import com.android.tools.r8.D8
import com.android.tools.r8.D8Command
import com.android.tools.r8.OutputMode
import io.github.classgraph.ClassGraph
import org.java_websocket.WebSocket
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }

    }
    dependencies {
        classpath("com.android.tools:r8:1.5.68")
        classpath("com.github.TooTallNate:Java-WebSocket:v1.4.0")
        classpath("io.github.classgraph:classgraph:4.8.53")
    }
}

tasks.register("writeOpModes") {
    dependsOn("compileClasses")
    val packageToScan = "org.firstinspires.ftc.teamcode"
    val prefix = "com.qualcomm.robotcore.eventloop.opmode."
    val annotations = arrayOf("Autonomous", "TeleOp")
    val classPackageRoot = "$buildDir/classes"
    //    println(classPackageRoot)
    // compileTask.destinationDir
    doLast {
        ClassGraph()
                // [Configure your ClassGraph instance here]
                .overrideClasspath(classPackageRoot)
                .whitelistPackages(packageToScan)
                .enableAnnotationInfo()
                .scan()
                .use { scanResult ->
                    File("$buildDir/dex/classes.txt").printWriter().use { out ->
                        for (annotation in annotations) {
                            val resultList = scanResult.getClassesWithAnnotation(prefix + annotation)
                            resultList.forEach {
                                val annotationInfo = it.getAnnotationInfo(prefix + annotation)
                                out.write(annotationInfo.parameterValues.getValue("name").toString())
                                out.write("/")
                                out.write(it.name)
                                out.write("/")
                            }
                            out.write("/n")
                        }
                    }
                }
    }
}


tasks.register("firecode") {
    dependsOn("writeOpModes")
}
tasks.register("clearClassesCache", Delete::class) {
    delete("$buildDir/classes")
    delete("$buildDir/dex/classes.dex")
    delete("$buildDir/dex/classes.txt")
    doLast {
        mkdir("$buildDir/dex/")
        mkdir("$buildDir/classes/")
    }
}
tasks.register("copyClasses", Copy::class) {
    dependsOn(":TeamCode:compileDebugKotlin")
    dependsOn("clearClassesCache")
    includeEmptyDirs = false
    val mainPath = "${project(":TeamCode").buildDir}/tmp/kotlin-classes/debug"
    val libPath = "${project(":MOELibraries").buildDir}/classes/kotlin/jvm/main"
    from(mainPath)
    from(libPath)
    include("**/*.class")
    into("$buildDir/classes")
    //    doFirst {
    //
    //    }
    //    delete( buildDir.toString()+('/classes'))
}
tasks.register("compileClasses") {
    dependsOn("copyClasses")
    val buildDir = this.project.buildDir
    val inputDir = File("$buildDir/classes")
    val outputDir = File("$buildDir/dex")
    doFirst {

        //        println("starting" + outputDir)
        val files = inputDir.walk().filter { it.isFile }.map { it.toPath() }.toList()

        val build = D8Command.builder()
                .addProgramFiles(files)
                .setMinApiLevel(23)
                .setDisableDesugaring(true)
                .setOutput(outputDir.toPath(), OutputMode.DexIndexed)
                .build()
        try {
            D8.run(build)
        } catch (e: RuntimeException) {
            println(e.toString())
        }
        //        println("done" + outputDir)
    }
}
tasks.register("temp") {
    doFirst {
        //                configurations.runtime.each { File f -> println f }

    }
}
