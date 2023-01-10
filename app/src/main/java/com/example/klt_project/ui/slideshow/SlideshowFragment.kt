package com.example.klt_project.ui.slideshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.klt_project.data.Report
import com.example.klt_project.databinding.FragmentSlideshowBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_slideshow.*
import org.w3c.dom.Text

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val database = Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
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

        val loadingText: EditText = binding.loadingTime
        val drivingText: EditText = binding.drivingTime
        val unloadingText: EditText = binding.unloadingTime
        val washingText: EditText = binding.washingTime

        slideshowViewModel.text.observe(viewLifecycleOwner) {
            loadTime = "${it["hour"]?.get(0).toString()}:${it["minutes"]?.get(0).toString()}:${it["seconds"]?.get(0).toString()}"
            drvTime = "${it["hour"]?.get(1).toString()}:${it["minutes"]?.get(1).toString()}:${it["seconds"]?.get(1).toString()}"
            unloadTime = "${it["hour"]?.get(2).toString()}:${it["minutes"]?.get(2).toString()}:${it["seconds"]?.get(2).toString()}"
            washTime = "${it["hour"]?.get(3).toString()}:${it["minutes"]?.get(3).toString()}:${it["seconds"]?.get(3).toString()}"

            loadingText.setText(loadTime)
            drivingText.setText(drvTime)
            unloadingText.setText(unloadTime)
            washingText.setText(washTime)
        }

        val showNote:EditText = binding.noteEdit
        slideshowViewModel.note.observe(viewLifecycleOwner){
            noteS = it
            showNote.setText(noteS)
        }

        binding.sendButton.setOnClickListener {
            val report = HashMap<String,Any>()
            loadingText.setText(loadingText.text.toString())
            drivingText.setText(drivingText.text.toString())
            unloadingText.setText(unloadingText.text.toString())
            washingText.setText(washingText.text.toString())
            showNote.setText(showNote.text.toString())
            report.put("loading time",loadingText.text.toString())
            report.put("driving time",drivingText.text.toString())
            report.put("unLoading time",unloadingText.text.toString())
            report.put("wash time",washingText.text.toString())
            report.put("note",showNote.text.toString())
            database.getReference("Mission").child(10.toString()).updateChildren(report)
            Log.d("Upload correct","yea")
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

