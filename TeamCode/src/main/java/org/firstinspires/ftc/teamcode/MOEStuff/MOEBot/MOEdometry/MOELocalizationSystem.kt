package org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEdometry

import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEChassis.MOEtion

interface MOELocalizationSystem {
    fun moetion(): MOEtion
    fun astarMoetion(): MOEtion {
        val original = moetion()
        return MOEtion(original.pose * 2.0, original.degAng)
    }
}
