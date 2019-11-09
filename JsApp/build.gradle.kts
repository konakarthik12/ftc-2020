//buildscript {
//    val kotlin_version = project.property("kotlin_version") as String
//    println(project)
////    project.proper{
////        set("kot","bot")
////    }
//
////
////    project.ext.set("kotlinx_html_version", "0.6.4")
////    ext.set("kotlinx_frontend_version", "0.0.30")
////    ext.set("react_version", "0.0.30")
//
////    ext.set("react_version", "16.4.0-pre.31-kotlin-$kotlin_version")
////    ext.set("react_dom_version", "16.4.0-pre.31-kotlin-$kotlin_version")
////    repositories {
////        mavenCentral()
////        maven {
////           artifactUrls("https://dl.bintray.com/kotlin/kotlin-eap")
////        }
////    }
////    dependencies {
////
////        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
////        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:${ext.get("kotlin_frontend_version")}")
////    }
//}
//plugins {
//    val kotlin_frontend_version:String by ext
//    id("kotlin2js")
//    id("org.jetbrains.kotlin.frontend").version(property("kotlin_frontend_version") as String)
//}
//repositories {
//    mavenLocal()
//    mavenCentral()
//    jcenter()
//    maven(url = "http://dl.bintray.com/kotlin/kotlin-dev")
//    maven(url = "http://dl.bintray.com/kotlinx/kotlinx")
//    maven(url ="http://dl.bintray.com/kotlin/kotlin-js-wrappers)
//
//}
//dependencies {
//    compile "org.jetbrains.kotlin:kotlin-stdlib-js:$kotlin_version"
//    compile "org.jetbrains.kotlinx:kotlinx-html-js:$kotlinx_html_version"
//    compile "org.jetbrains:kotlin-react:$react_version"
//    compile "org.jetbrains:kotlin-react-dom:$react_dom_version"
//}
//kotlinFrontend {
//    npm {
//        dependency "style-loader" // production dependency
//        dependency "react"
//        dependency "react-dom"
//        dependency "kotlin"
//        dependency "@jetbrains/kotlin-extensions"
//        dependency "@jetbrains/kotlin-react"
//    }
//    webpackBundle {
//        bundleName = "main"
//        sourceMapEnabled = false   // enable/disable source maps
//        contentPath = file("${projectDir}/public") // a file that represents a directory to be served by dev server)
//        publicPath = "/"  // web prefix
//        host = "localhost" // dev server host
//        port = 8088   // dev server port
//        stats = "errors-only"  // log level
//    }
//}
//task copyDocs (type: Copy) {
//    println ":md-react:copyDocs: Copying to public directory"
//    from("${projectDir}/build/bundle") {
//        include "**/*.js"
//        include "*.js"
//    }
//    into "${projectDir}/public/static"
//    println ":md-react:copyDocs: Done copying"
//}
//task assembleWeb (type: Sync) {
//    configurations.compile.each {
//        File file ->
//        from(zipTree(file.absolutePath), {
//            includeEmptyDirs = false
//            include { fileTreeElement ->
//                def path = fileTreeElement . path
//                        (path.endsWith(".js") || path.endsWith(".map")) && (path.startsWith("META-INF/resources/") ||
//                        !path.startsWith("META-INF/"))
//            }
//        })
//    }
//    from compileKotlin2Js . destinationDir
//            into "${projectDir}/build/classes/main"
//    dependsOn classes
//}
////run.dependsOn copyDocs
//assemble.dependsOn assembleWeb
//        copyDocs.dependsOn bundle
////assemble.finalizedBy(copyDocs)
//        compileKotlin2Js {
//            kotlinOptions.outputFile = "${projectDir}/build/classes/main/web.js"
//            kotlinOptions.moduleKind = "umd"
//            kotlinOptions.sourceMap = true
//        }