@file:Suppress("DEPRECATION")

package com.example.klt_project.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.klt_project.data.User
import com.example.klt_project.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private val database = Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.register.setOnClickListener {
            val email = binding.username.text.toString()
            val password = binding.password.text.toString()
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val user = User(firstName, lastName, email, password)
            auth = Firebase.auth
            FirebaseAuth.getInstance()
            this.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                it.user?.let { it1 ->
                    database.getReference("Users").child(it1.uid).setValue(user).addOnSuccessListener {
                        binding.firstName.text.clear()
                        binding.lastName.text.clear()
                        binding.password.text.clear()
                        binding.username.text.clear()
                        Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show()
                        auth.signOut()
                    }.addOnFailureListener{
                        Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


}