import org.firstinspires.ftc.teamcode.external.AdvancedMath.Point

external fun require(module:String):dynamic

fun main(args: Array<String>) {
    println("Hello JavaScript!")

    val express = require("express")
    val app = express()

    app.get("/") { _, res ->
        res.type("text/plain")
        res.send("Kotlin/JS is kool"+Point(2.0,4.0))
    }

    app.listen(3000) {
        println("Listening on port 3000")
    }
}