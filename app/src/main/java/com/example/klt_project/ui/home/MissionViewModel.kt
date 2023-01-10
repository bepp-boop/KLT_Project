package com.example.klt_project.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class MissionViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth

    private val _data = MutableLiveData<HashMap<Any, Any>>()
    val data: LiveData<HashMap<Any, Any>> = _data

    private val database =
        FirebaseDatabase.getInstance("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
    private val ref = database.getReference("Users")

    fun grabData() {
        auth = Firebase.auth
        FirebaseAuth.getInstance()

        auth.currentUser?.let { it ->
            ref.child(it.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
//                    val data = snapshot.children.map {
//                        it.value
//                    }
//                    _data.value = listOf(data)
                    val data = snapshot.value
                    _data.value = data as HashMap<Any, Any>?
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }
}
