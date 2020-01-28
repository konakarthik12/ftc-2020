//import com.pushy.tasks.compileStuff
import com.android.tools.r8.D8
import com.android.tools.r8.D8Command
import com.android.tools.r8.OutputMode
import io.github.classgraph.ClassGraph

buildscript {
    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath("com.android.tools:r8:1.5.70")
        classpath("io.github.classgraph:classgraph:4.8.60")
    }
}

tasks.register("writeOpModes") {
    dependsOn("compileClasses")
    val packageToScan = "org.firstinspires.ftc.teamcode"
    val prefix = "com.qualcomm.robotcore.eventloop.opmode."
    val annotations = arrayOf("Autonomous", "TeleOp").map { prefix + it }
    val disabledAnnotation = prefix + "Disabled"
    val classPackageRoot = "$buildDir/classes"
    val outFile = File("$buildDir/dex/classes.txt")
    doLast {
        val scanResult =
                ClassGraph()
                        .overrideClasspath(classPackageRoot)
                        .whitelistPackages(packageToScan)
                        .enableAnnotationInfo()
                        .scan()

        val out = outFile.printWriter()
        for (annotation in annotations) {
            val annotatedClasses =
                    scanResult.getClassesWithAnnotation(annotation).filterNot { it.hasAnnotation(disabledAnnotation) }
            annotatedClasses.forEach { clazz ->
                val annotationInfo = clazz.getAnnotationInfo(annotation)

                var toString = annotationInfo.parameterValues.getValue("name")?.toString()
                if (toString.isNullOrEmpty()) {
                    toString = clazz.simpleName
                }
                out.write(toString!!)
                out.write("/")
                out.write(clazz.name)
                out.write("/")
            }
            out.write("\n")
        }
        out.close()
        scanResult.close()
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
//    val libPath = "${project(":MOELibraries").buildDir}/classes/kotlin/jvm/main"
    from(mainPath)
//    from(libPath)
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
//tasks.register("temp") {
//    doFirst {
//        //                configurations.runtime.each { File f -> println f }
//
//    }
//}
