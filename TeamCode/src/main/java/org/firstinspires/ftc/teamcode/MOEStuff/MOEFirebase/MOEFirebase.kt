package org.firstinspires.ftc.teamcode.MOEStuff.MOEFirebase;

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import org.firstinspires.ftc.robotcontroller.moeglobal.firebase.MOEConfig
import org.firstinspires.ftc.teamcode.constants.ReferenceHolder

interface MOEFirebase : ChildEventListener {

    //    var localConfigSnapshot: DataSnapshot
    fun getCustomRef(ref: DatabaseReference): DatabaseReference? {
        return null
    }

    fun onConfigChanged(dataSnapshot: DataSnapshot) {

    }

    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onChildAdded(p0: DataSnapshot, p1: String?) {
    }

    override fun onChildChanged(p0: DataSnapshot, p1: String?) {

    }

    override fun onChildRemoved(p0: DataSnapshot) {

    }

    override fun onChildMoved(p0: DataSnapshot, p1: String?) {
    }

    fun addListener(): DatabaseReference? {
        val customRef = getCustomRef(MOEConfig.config)?:return null
        customRef.addValueEventListener(object : MOEEventListener() {
            override fun onDataChange(snapshot: DataSnapshot) {
                onConfigChanged(snapshot)
            }
        })
     return customRef
        //        customRef.addChildEventListener(this)

    }
}