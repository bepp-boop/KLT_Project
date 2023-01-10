package com.example.klt_project.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.klt_project.R
import com.example.klt_project.ui.home.ui.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_mission.*



class MissionActivity : AppCompatActivity() {
   // private val database = Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
   // private val myRef = database.getReference("Mission")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        view_pager.adapter = adapter

        TabLayoutMediator(tabLayout, view_pager){tab, position->
            when(position){
                0->{
                    tab.text="Mission"
                }
                1->{
                    tab.text="Schedule"
                }
                2->{
                    tab.text="Note"
                }
            }
        }.attach()

    }

}