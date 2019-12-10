package org.firstinspires.ftc.teamcode.test

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEFancyPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOEPidValues
import org.firstinspires.ftc.teamcode.MOEStuff.MOEBot.MOEPid.MOETurnPid
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder.Companion.moeOpMode
import org.firstinspires.ftc.teamcode.utilities.addData
import org.firstinspires.ftc.teamcode.utilities.get
import org.firstinspires.ftc.teamcode.utilities.toFixed

@TeleOp(name = "DiagonalStrafeTest")
class DiagonalStrafeTest : MOETeleOp() {
    override fun initOpMode() {

    }

    lateinit var pid: MOETurnPid

    override fun mainLoop() {

    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        val options = dataSnapshot.getValue(MOEPidValues::class.java)!!
        Log.e("stuff",options.toString())
        pid = MOETurnPid(options)
    }
}