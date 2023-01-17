package com.example.klt_project.ui.home.ui.noteFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.klt_project.DataList
import com.example.klt_project.databinding.FragmentNoteBinding
import com.google.android.material.internal.ViewUtils
import com.google.android.material.textfield.TextInputLayout


class NoteFragment:Fragment(){
    private var _binding:FragmentNoteBinding? = null
    private val binding get() = _binding!!
    @SuppressLint("RestrictedApi", "ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        binding.saveNote.setOnClickListener {
            val textInputLayout: TextInputLayout = binding.note.findViewById(com.example.klt_project.R.id.note)
            val text: Editable? = textInputLayout.editText!!.text
            DataList.note = text.toString()
        }
        return binding.root
    }


}