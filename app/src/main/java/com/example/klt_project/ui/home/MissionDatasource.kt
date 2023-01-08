package com.example.klt_project.ui.home

import com.example.klt_project.R

class MissionDatasource {

    fun loadMissions(): List<Missions>{
        return listOf(
            Missions(R.string.first_mission, R.string.city_from, R.string.city_to,R.string.expected_load_time,R.string.expected_unload_time),
            //Missions(R.string.mission, R.string.city_to, R.string.city_from,R.string.expected_unload_time,R.string.expected_load_time)
        )
    }
}