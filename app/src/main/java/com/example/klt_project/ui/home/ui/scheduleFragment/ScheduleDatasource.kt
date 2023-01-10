package com.example.klt_project.ui.home.ui.scheduleFragment

import android.annotation.SuppressLint
import com.example.klt_project.R

class ScheduleDatasource {
    @SuppressLint("ResourceType")
    fun loadSchedule(): List<Schedule> {
        return listOf(
            Schedule(
                R.array.loading
            ),
            Schedule(
                R.array.driving
            ),
            Schedule(
                R.array.unloading
            ),
            Schedule(
                R.array.wash
            ),
        )

    }
}