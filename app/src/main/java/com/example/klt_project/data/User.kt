package com.example.klt_project.data

data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val password: String? = null,
    val missions_id: MutableList<Int>,
)

data class Report(
    val loadingTime:String = "",
    val drivingTime:String = "",
    val unloadingTime:String = "",
    val washingTime:String = "",
    val note:String = ""
)