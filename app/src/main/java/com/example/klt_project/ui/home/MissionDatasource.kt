package com.example.klt_project.ui.home

import com.example.klt_project.R

class MissionDatasource {
    fun loadMissions(): List<Missions>{
        return listOf(
            Missions(R.string.mission, R.string.from, R.string.to),
            Missions(R.string.mission, R.string.from, R.string.to),
            Missions(R.string.mission, R.string.from, R.string.to),
            Missions(R.string.mission, R.string.from, R.string.to),
            Missions(R.string.mission, R.string.from, R.string.to)
        )
    }
}