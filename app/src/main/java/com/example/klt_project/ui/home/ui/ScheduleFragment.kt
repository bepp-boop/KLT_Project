package com.example.klt_project.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.klt_project.databinding.FragmentScheduleBinding

class ScheduleFragment: Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var text:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val scheduleDataset = ScheduleDatasource().loadSchedule()
        val recyclerView = binding.scheduleRecycler
        recyclerView.adapter = ScheduleItemAdapter(this, scheduleDataset)
        recyclerView.setHasFixedSize(true)
        return binding.root
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
    }

}