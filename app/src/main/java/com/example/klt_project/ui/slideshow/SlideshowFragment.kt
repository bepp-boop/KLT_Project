package com.example.klt_project.ui.slideshow

import android.annotation.SuppressLint
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.klt_project.databinding.FragmentSlideshowBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class SlideshowFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var _binding: FragmentSlideshowBinding? = null
    private val database =
        Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        slideshowViewModel =
            ViewModelProvider(this)[SlideshowViewModel::class.java]

        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "RestrictedApi", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        root.setOnTouchListener { _, _ ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
            false
        }

        var loadTime: String
        var drvTime: String
        var unloadTime: String
        var washTime: String
        var noteS: String

        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val loadingText: EditText = binding.loadingTime
        val drivingText: EditText = binding.unloadingTime
        val unloadingText: EditText = binding.drivingTime
        val washingText: EditText = binding.washingTime

        slideshowViewModel.saveTime.observe(viewLifecycleOwner) {
            loadTime = it[0]
            drvTime = it[1]
            unloadTime = it[2]
            washTime = it[3]

            loadingText.setText(loadTime)
            drivingText.setText(drvTime)
            unloadingText.setText(unloadTime)
            washingText.setText(washTime)
        }

        val showNote: EditText = binding.noteEdit
        slideshowViewModel.note.observe(viewLifecycleOwner) {
            noteS = it
            showNote.setText(noteS)
        }

        binding.sendButton.setOnClickListener {
            val report = HashMap<String, Any>()
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
//            database.getReference("Mission").child(10.toString()).updateChildren(report)

            val date = "${LocalDate.now().dayOfMonth}-${LocalDate.now().monthValue}-${LocalDate.now().year}" +
                    " ${LocalTime.now().hour}:${LocalTime.now().minute}:${LocalTime.now().second}"
            Log.d("date", date)
            database.getReference("Mission").child(date).updateChildren(report)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

