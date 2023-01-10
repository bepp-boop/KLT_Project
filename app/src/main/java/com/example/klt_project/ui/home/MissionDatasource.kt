package com.example.klt_project.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
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

    fun loadMissions(): List<Missions> {

        return listOf(
            Missions(
                R.string.first_mission,
                R.string.city_from,
                R.string.city_to,
                R.string.expected_load_time,
                R.string.expected_unload_time
            ),

            Missions(
                R.string.mission,
                R.string.city_to,
                R.string.city_from,
                R.string.expected_unload_time,
                R.string.expected_load_time)
        )

    }
}