package com.example.klt_project.ui.home.ui.ScheduleFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.klt_project.DataList
import com.example.klt_project.databinding.FragmentScheduleBinding

class ScheduleFragment: Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val scheduleDataset = ScheduleDatasource().loadSchedule()
        val recyclerView = binding.scheduleRecycler

        recyclerView.adapter = ScheduleItemAdapter(this, scheduleDataset)
        recyclerView.setHasFixedSize(true)
        return binding.root
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        Log.d("timer", "${DataList.timeElapsed["hour"]?.asList()}:${DataList.timeElapsed["minutes"]?.asList()}:${DataList.timeElapsed["seconds"]?.asList()}")
        //Log.d("timer", "${DataList.timeElapsed["hour"].toString()}:${DataList.timeElapsed["minutes"].toString()}:${DataList.timeElapsed["seconds"].toString()}")
    }

}