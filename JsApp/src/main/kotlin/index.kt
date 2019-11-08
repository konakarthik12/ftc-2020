import org.firstinspires.ftc.teamcode.external.AdvancedMath.Point
import kotlin.js.Promise
external fun require(module:String):dynamic

fun main(args: Array<String>) {
    println("Hello JavaScript!")

    val express = require("express")
    val app = express()

    app.get("/") { _, res ->
        res.type("text/plain")
        res.send("Kotlin/JS is kool"+Point(2.0,4.0))
    }
    val ngrok = require("ngrok");

    app.listen(3000) {
        println("Server Started")
    
    launch{
    val url = (ngrok.connect(3000) as Promise<dynamic>).await();
    println(url)
    }
    
    }


}