package org.firstinspires.ftc.teamcode.external

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}. I have ${Sample().checkMe()}"