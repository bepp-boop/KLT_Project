package com.example.klt_project.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.klt_project.R
import com.example.klt_project.databinding.ActivityMissionNewBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class MissionNew : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val database =
        Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
    private lateinit var binding: ActivityMissionNewBinding

    @SuppressLint("RestrictedApi", "ClickableViewAccessibility")
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
        mDate.setText(LocalDate.now().toString())

        val create = binding.create
        setContentView(binding.root)
        val view = window.decorView
        view.setOnTouchListener{v, event ->
            hideKeyboard(v)
            false
        }

        create.setOnClickListener {

            val date = mDate.text.toString()
            val addressFrom = mAddressFrom.text.toString()
            val addressTo = mAddressTo.text.toString()
            val woodPallets = mWoodPallets.text.toString()
            val plasticPallets = mPlasticPallets.text.toString()
            if (date.isEmpty()){
                mDate.error = "Date is require"
                mDate.requestFocus()
                return@setOnClickListener
            }
            if (addressFrom.isEmpty()){
                mAddressFrom.error = "Address is require"
                mAddressFrom.requestFocus()
                return@setOnClickListener
            }
            if (addressTo.isEmpty()){
                mAddressTo.error = "Address is require"
                mAddressTo.requestFocus()
                return@setOnClickListener
            }
            if (woodPallets.isEmpty()){
                mWoodPallets.error = "Number of Wood Pallets is require"
                mWoodPallets.requestFocus()
                return@setOnClickListener
            }
            if (plasticPallets.isEmpty()){
                mPlasticPallets.error = "Number of Plastic Pallet is require"
                mPlasticPallets.requestFocus()
                return@setOnClickListener
            }
            val mission = Mission(date, addressFrom, addressTo, woodPallets, plasticPallets)
            auth = Firebase.auth
            FirebaseAuth.getInstance()
            val ref = database.getReference("Mission")
            val getKey = ref.push()
            auth.currentUser.let {
                it?.let { it1 ->
                    getKey.setValue(mission).addOnSuccessListener {
                        binding.addressTo.text.clear()
                        binding.addressFrom.text.clear()
                        binding.palletsWood.text.clear()
                        binding.palletsPlastic.text.clear()
                        Toast.makeText(this, "Mission created!", Toast.LENGTH_SHORT).show()
                        database.getReference("Users").child(it1.uid).child("missions_id")
                            .child(getKey.key.toString()).setValue("").addOnSuccessListener {
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, "Mission creation failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    data class Mission(
        val date: String? = null,
        val addressFrom: String? = null,
        val addressTo: String? = null,
        val woodPallets: String? = null,
        val plasticPallets: String? = null,
    )
}