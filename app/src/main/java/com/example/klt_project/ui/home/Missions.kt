package com.example.klt_project.ui.home

import androidx.annotation.StringRes

data class Missions(
    @StringRes val stringMissionResourceId: Int,
    @StringRes val stringFromResourceId: Int,
    @StringRes val stringToResourceId: Int,
    @StringRes val stringLoadResourceId: Int,
    @StringRes val stringUnloadResourceId:Int

    ) {
}