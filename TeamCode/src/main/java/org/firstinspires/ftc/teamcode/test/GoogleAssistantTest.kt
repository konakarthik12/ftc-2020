package org.firstinspires.ftc.teamcode.test

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.MOEStuff.MOEOpmodes.MOETeleOp

@Disabled
@TeleOp(name = "Google Assistant Test")
class GoogleAssistantTest : MOETeleOp() {
    var goal = 0
    override fun initOpMode() {
        ref.setValue(null)
    }

    override fun mainLoop() {

        telemetry.addData("nothing", goal.toString())
        telemetry.update()
    }

    override fun onConfigChanged(dataSnapshot: DataSnapshot) {
        if(dataSnapshot.childrenCount==0L) return
        val toInt = dataSnapshot.children.last().child("degrees").value.toString().toInt()
        goal += toInt

    }

    override fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return ref.child("assistant").child("turn")
    }

}