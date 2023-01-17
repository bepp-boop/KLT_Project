package com.example.klt_project.ui.slideshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.klt_project.databinding.FragmentSlideshowBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val database = Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("SetTextI18n", "RestrictedApi", "ClickableViewAccessibility")
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
            report["loading time"] = loadingText.text.toString()
            report["driving time"] = drivingText.text.toString()
            report["unLoading time"] = unloadingText.text.toString()
            report["wash time"] = washingText.text.toString()
            report["note"] = showNote.text.toString()
            database.getReference("Mission").child(10.toString()).updateChildren(report)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

