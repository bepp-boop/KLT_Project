package com.example.klt_project.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.klt_project.R
import com.example.klt_project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this)[MissionViewModel::class.java]
        val missionDataset = MissionDatasource().loadMissions()
        val recyclerView = binding.recycler
        recyclerView.adapter = MissionItemAdapter(this, missionDataset)
        viewModel.grabData()
//        viewModel.data.observe(viewLifecycleOwner) {
//            DataList.userMission = it["missions_id"] as HashMap<Any, Any>
//            Log.d("map", DataList.userMission["-NLQDXD0U43Ux5E84aVm"].toString())
//            binding.missionText.text = it["missions_id"].toString()
//        }
        recyclerView.setHasFixedSize(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
