package com.example.klt_project.data

import android.util.Log
import com.example.klt_project.DataList
import com.example.klt_project.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private lateinit var auth: FirebaseAuth
    private val database = Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
    private val myRef = database.getReference("Users")

    fun login(username: String, password: String): Result<LoggedInUser>? {
        return try {
            auth = Firebase.auth
            FirebaseAuth.getInstance()

            val user = LoggedInUser(
                auth.signInWithEmailAndPassword(username, password).toString(),
                username
            )
            if (auth.currentUser != null) {
                myRef.child(auth.uid.toString()).get().addOnSuccessListener {
                    if(it.exists()) {
                        DataList.missionsID.clear()
                        DataList.missionsID = it.child("missions_id").value as ArrayList<Int>
                        Log.d("mission", "from loginDataSource: ${DataList.missionsID}")
                    }
                }
                Result.Success(user)
            } else {
                Result.Error(IOException("Error logging in"))
            }

        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        auth.signOut()
    }
}