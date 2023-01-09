package com.example.klt_project.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.klt_project.DataList
import com.example.klt_project.DataList.userMission
import com.example.klt_project.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.currentCoroutineContext
import kotlin.coroutines.coroutineContext

class MissionDatasource {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database =
        Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
    private val myRef = database.getReference("Mission")

    fun loadMissions(): List<Missions> {

        Log.d("mission", "from missionDataSource: ${DataList.missionsID}")

//        myRef.child("10")
//            .addValueEventListener(object : ValueEventListener {
//                @SuppressLint("SetTextI18n")
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    userMission.clear()
//                    userMission["from"] = snapshot.child("place of dispatch").value as? String
//                    userMission["to"] = snapshot.child("place of destinations").value as? String
//                    //Log.d("mission", userMission["from"].toString())
//                    //userMission["email"] = snapshot.child("email").value as? String
//                    //DataList.missionsID = snapshot.child("missions_id").value as? ArrayList<Int>
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })

        return listOf(
            Missions(
                R.string.first_mission,
                R.string.city_from,
                R.string.city_to,
                R.string.expected_load_time,
                R.string.expected_unload_time
            ),

            //Missions(R.string.mission, R.string.city_to, R.string.city_from,R.string.expected_unload_time,R.string.expected_load_time)
        )

    }
}