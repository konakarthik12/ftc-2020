//import com.pushy.tasks.compileStuff
import com.android.tools.r8.D8
import com.android.tools.r8.D8Command
import com.android.tools.r8.OutputMode
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
    }
}



tasks.register("firecode") {
    dependsOn("compileClasses")
}
tasks.register("clearClassesCache", Delete::class) {
    doFirst {
        delete("$buildDir/firecode/classes")
        delete("$buildDir/firecode/classes.dex")
    }
}
tasks.register("copyClasses", Copy::class) {
    dependsOn(":TeamCode:compileDebugKotlin")
    dependsOn("clearClassesCache")
    doFirst {
        includeEmptyDirs = false
        val mainPath = "${project(":TeamCode").buildDir}/tmp/kotlin-classes/debug"
        val libPath = "${project(":MOELibraries").buildDir}/classes/kotlin/jvm/main"
        //    println(message)
        from(mainPath)
        from(libPath)
        include("**/*.class")
        into("$buildDir/firecode/classes")
    }
    //    delete( buildDir.toString()+('/classes'))
}
tasks.register("compileClasses") {
    dependsOn("copyClasses")
    doFirst {
        val buildDir = this.project.buildDir
        val inputDir = File("$buildDir/firecode/classes")
        val outputDir = File("$buildDir/firecode/")
        println("starting" + outputDir)
        val files = inputDir.walkTopDown().filter { it.isFile }.map { it.toPath() }.toList()

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
        println("done" + outputDir)
    }
}