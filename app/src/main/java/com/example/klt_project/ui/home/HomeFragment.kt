package com.example.klt_project.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.klt_project.DataList
import com.example.klt_project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    lateinit var MissionID:ArrayList<Int>
    var string = DataList.missionsID.toString()
    lateinit var myMissionID: String
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val bundle = arguments
//        val output = bundle?.getString("notMission")
//        if (output != null) {
//          Log.d("Something important", output)
//        }
//        else Log.d("Something important","Damn")
        getMissionID()

        string = setMissionID(DataList.missionsID.toString()).toString()
        //val string = DataList.missionsID.toString()


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val missionDataset = MissionDatasource().loadMissions()
        val recyclerView = binding.recycler
        recyclerView.adapter = MissionItemAdapter(this, missionDataset)
        recyclerView.setHasFixedSize(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMissionID(){
        MissionID = ArrayList()
        for (i in 0 until DataList.missionsID.size){
            MissionID.add(DataList.missionsID[i])
        }
        Log.d("important", MissionID.toString())
    }

    private fun setMissionID(missionID: String){
        this.myMissionID = missionID
    }
}
