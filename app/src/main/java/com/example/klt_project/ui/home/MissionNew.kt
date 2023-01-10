package com.example.klt_project.ui.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.klt_project.R
import com.example.klt_project.databinding.ActivityMissionNewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import kotlin.random.Random

class MissionNew : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val database =
        Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
    private lateinit var binding: ActivityMissionNewBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission_new)
        binding = ActivityMissionNewBinding.inflate(layoutInflater)
        val mDate: EditText = binding.date
        val mAddressFrom: EditText = binding.addressFrom
        val mAddressTo: EditText = binding.addressTo
        val mWoodPallets: EditText = binding.palletsWood
        val mPlasticPallets: EditText = binding.palletsPlastic
        val missionID = Random.nextInt(0,100000).toString()
        mDate.setText(LocalDate.now().toString())

        val create = binding.create
        setContentView(binding.root)

        create.setOnClickListener {
            val date = mDate.text.toString()
            val addressFrom = mAddressFrom.text.toString()
            val addressTo = mAddressTo.text.toString()
            val woodPallets = mWoodPallets.text.toString()
            val plasticPallets = mPlasticPallets.text.toString()
            val mission = Mission(missionID, date, addressFrom, addressTo, woodPallets, plasticPallets)
            auth = Firebase.auth
            FirebaseAuth.getInstance()
            auth.currentUser.let { it ->
                it?.let { it1 ->
                    database.getReference("Mission").child(missionID).setValue(mission).addOnSuccessListener {
                        binding.addressTo.text.clear()
                        binding.addressFrom.text.clear()
                        binding.palletsWood.text.clear()
                        binding.palletsPlastic.text.clear()
                        Toast.makeText(this, "Mission created!", Toast.LENGTH_SHORT).show()
                        database.getReference("Users").child(it1.uid).child("missions_id").setValue(missionID).addOnSuccessListener {
                        }
                    }.addOnFailureListener{
                        Toast.makeText(this, "Mission creation failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    data class Mission(
        val ID: String? = null,
        val date: String? = null,
        val addressFrom: String? = null,
        val addressTo: String? = null,
        val woodPallets: String? = null,
        val plasticPallets: String? = null,
    )
}