package com.example.klt_project.data

data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val password: String? = null,
    val missions_id: MutableList<Int>,
)

