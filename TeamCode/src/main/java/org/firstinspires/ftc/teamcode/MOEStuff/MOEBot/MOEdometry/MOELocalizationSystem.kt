package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEtion

interface MOELocalizationSystem {
    fun moetion(): MOEtion
    fun astarMoetion() = moetion().apply { this.pose*2.0 }
}
