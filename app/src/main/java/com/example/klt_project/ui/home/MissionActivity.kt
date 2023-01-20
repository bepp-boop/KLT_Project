package com.example.klt_project.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.klt_project.R
import com.example.klt_project.ui.home.ui.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_mission.*
import kotlinx.android.synthetic.main.list_interaction.*


class MissionActivity : AppCompatActivity() {

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