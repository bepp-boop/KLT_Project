package com.example.klt_project.ui.slideshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.klt_project.data.Report
import com.example.klt_project.databinding.FragmentSlideshowBinding
import org.w3c.dom.Text

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this)[SlideshowViewModel::class.java]

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var loadTime = ""
        var drvTime  = ""
        var unloadTime = ""
        var washTime = ""
        var noteS = ""

        val loadingText: TextView = binding.loadingTime
        val drivingText: TextView = binding.drivingTime
        val unloadingText: TextView = binding.unloadingTime
        val washingText: TextView = binding.washingTime

        slideshowViewModel.text.observe(viewLifecycleOwner) {
            loadTime = "${it["hour"]?.get(0).toString()}:${it["minutes"]?.get(0).toString()}:${it["seconds"]?.get(0).toString()}"
            drvTime = "${it["hour"]?.get(1).toString()}:${it["minutes"]?.get(1).toString()}:${it["seconds"]?.get(1).toString()}"
            unloadTime = "${it["hour"]?.get(2).toString()}:${it["minutes"]?.get(2).toString()}:${it["seconds"]?.get(2).toString()}"
            washTime = "${it["hour"]?.get(3).toString()}:${it["minutes"]?.get(3).toString()}:${it["seconds"]?.get(3).toString()}"

            loadingText.text = loadTime
            drivingText.text = drvTime
            unloadingText.text = unloadTime
            washingText.text = washTime
        }

        val showNote:TextView = binding.noteEdit
        slideshowViewModel.note.observe(viewLifecycleOwner){
            noteS = it
            showNote.text = noteS
        }
        val report = Report(loadTime, drvTime, unloadTime, washTime, noteS)
        Log.d("report", report.note)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

