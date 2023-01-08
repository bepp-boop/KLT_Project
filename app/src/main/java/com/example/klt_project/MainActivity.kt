package com.example.klt_project

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.klt_project.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.collections.set


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var missions: ArrayList<Int>? = null
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
    private val myRef = database.getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener {
            //this will be used to create a new mission. Ex: picking up empty pallets.
        }
        val uid:String = this.auth.currentUser?.uid.toString()
        Log.d("firebase", uid)
        if(uid.isNotEmpty()){
            getUserData(uid)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_about_us, R.id.nav_slideshow
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }


    @SuppressLint("SetTextI18n")
    private fun getUserData(user: String) {
        val userData = hashMapOf<String?, String?>()
        Log.d("firebase", "inside getUserData")
        Log.d("firebase", "user: $user")

        myRef.child(user).addValueEventListener(object : ValueEventListener{
            @SuppressLint("SetTextI18n")

            override fun onDataChange(snapshot: DataSnapshot) {
                userData.clear()
                Log.d("firebase", "inside onDataChange")
                userData["firstName"] = snapshot.child("firstName").value as? String
                userData["lastName"] = snapshot.child("lastName").value as? String
                userData["email"] = snapshot.child("email").value as? String
                missions = snapshot.child("missions_id").value as? ArrayList<Int>
                //DataList.missionsID = (missions)!!

//                val homeFragment = HomeFragment()
//                val bundle = Bundle()
//                //bundle.putIntegerArrayList("missions", missions)
//                bundle.putString("notMission","Something")
//                //set MyFragment Arguments
//                homeFragment.arguments = bundle



                Log.i("firebase", userData["firstName"].toString())

                val navView: NavigationView = binding.navView
                val headView: View = navView.getHeaderView(0)
                val name: TextView = headView.findViewById(R.id.logged_user)
                val emailShow: TextView = headView.findViewById(R.id.user_email)
                val logOut: Button = headView.findViewById(R.id.log_out)
                logOut.setOnClickListener {
                    val auth:FirebaseAuth = FirebaseAuth.getInstance()
                    auth.signOut()
                    finish()
                }
                name.text = "${userData["firstName"]} ${userData["lastName"]}"
                emailShow.text = userData["email"]
                //emailShow.text = missions.toString()
                //name.text = "$firstName $lastName"
                //emailShow.text = email

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Network error", Toast.LENGTH_SHORT).show()
            }
        })

//        myRef.child(user).get().addOnSuccessListener {
//            if(it.exists()) {
//                val firstName = it.child("firstName").value as String
//                val lastName = it.child("lastName").value as String
//                val email = it.child("email").value as String
//
//                Log.i("register", firstName)
//
//                val navView: NavigationView = binding.navView
//                val headView: View = navView.getHeaderView(0)
//                val name: TextView = headView.findViewById(R.id.logged_user)
//                val emailShow: TextView = headView.findViewById(R.id.user_email)
//                name.text = "$firstName $lastName"
//                emailShow.text = email
//
//            }
//        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        val uid:String = auth.currentUser?.uid.toString()
        Log.d("firebase", "onAnimation: $uid")
        if(uid.isNotEmpty()){
            getUserData(uid)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        auth.signOut()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

object DataList{
    var missionsID: ArrayList<Int> = arrayListOf()
}