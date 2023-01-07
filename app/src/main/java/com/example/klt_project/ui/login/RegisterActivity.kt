@file:Suppress("DEPRECATION")

package com.example.klt_project.ui.login

import android.os.Bundle
import android.util.Patterns
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
            val missionsId: MutableList<Int> = ArrayList()

            if (email.isEmpty()){
                binding.username.error = "Email is required"
                binding.username.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.password.error = "Password is required"
                binding.password.requestFocus()
                return@setOnClickListener
            }
            if (firstName.isEmpty()){
                binding.firstName.error = "First name is required"
                binding.firstName.requestFocus()
                return@setOnClickListener
            }
            if (lastName.isEmpty()){
                binding.lastName.error = "Last name is required"
                binding.lastName.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.username.error = "Provide valid Email"
                binding.username.requestFocus()
                return@setOnClickListener
            }
            if(password.length < 6){
                binding.password.error = "Min password length should be 6 character"
                binding.password.requestFocus()
                return@setOnClickListener
            }
            //Add a index here so the array is not null
            missionsId.add(10)
            val user = User(firstName, lastName, email, password,missionsId)
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