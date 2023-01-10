package com.example.klt_project.ui.home.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.klt_project.ui.home.ui.NoteFragment.NoteFragment
import com.example.klt_project.ui.home.ui.ScheduleFragment.ScheduleFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                PdfFragment()
            }
            1->{
                ScheduleFragment()
            }
            2->{
                NoteFragment()
            }
            else->{
                Fragment()
           }
        }
    }
}