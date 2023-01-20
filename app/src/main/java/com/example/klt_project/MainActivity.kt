package com.example.klt_project

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.klt_project.DataList.missionsID
import com.example.klt_project.data.Result
import com.example.klt_project.databinding.ActivityMainBinding
import com.example.klt_project.ui.home.MissionNew
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlin.collections.set


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    //private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var auth: FirebaseAuth
    private val database = Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
    private val myRef = database.getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener {
            val intent = Intent(this, MissionNew::class.java)
            startActivity(intent)
            //this will be used to create a new mission. Ex: picking up empty pallets.
        }
        auth.signInWithEmailAndPassword("test3@gmail.com","123456").addOnCompleteListener{

            if(it.isCanceled ){
                Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Welcome KLT", Toast.LENGTH_SHORT).show()
            }
            getUserData(this.auth.currentUser?.uid.toString())
            Log.d("user", it.isSuccessful.toString())
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

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStack()
        }else {
            super.onBackPressed()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun getUserData(user: String) {
        val userData = hashMapOf<String?, String?>()
        Log.d("firebase", "inside getUserData")
        Log.d("firebase", "user: $user")

        myRef.child(user).addValueEventListener(object : ValueEventListener{
            @SuppressLint("SetTextI18n")
            @Suppress("UNCHECKED_CAST")
            override fun onDataChange(snapshot: DataSnapshot) {
                userData.clear()
                Log.d("mission", "inside onDataChange")
                userData["firstName"] = snapshot.child("firstName").value as? String
                userData["lastName"] = snapshot.child("lastName").value as? String
                userData["email"] = snapshot.child("email").value as? String

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
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Network error", Toast.LENGTH_SHORT).show()
            }
        })
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
        auth.signOut()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

object DataList{

    var saveTime: ArrayList<String> = arrayListOf("Loading time", "Driving time", "Unloading time", "Washing time")

    var missionsID: ArrayList<Int> = arrayListOf(0,0,0,0,0,0,0,0,0,0,0)

    var timeElapsed = hashMapOf<String, Array<Int>?>()

    var buttonArray: ArrayList<Int> = arrayListOf(0,0,0,0)

    var note:String = ""

}