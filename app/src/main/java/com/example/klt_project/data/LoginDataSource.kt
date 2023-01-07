package com.example.klt_project.data

import com.example.klt_project.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private lateinit var auth: FirebaseAuth

    fun login(username: String, password: String): Result<LoggedInUser>? {
        return try {
            auth = Firebase.auth
            FirebaseAuth.getInstance()
            val user = LoggedInUser(
                auth.signInWithEmailAndPassword(username, password).toString(),
                username
            )
            Result.Success(user)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        auth.signOut()
    }
}