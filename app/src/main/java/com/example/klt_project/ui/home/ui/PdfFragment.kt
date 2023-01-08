package com.example.klt_project.ui.home.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.klt_project.databinding.FragmentPdfBinding
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.android.synthetic.main.fragment_pdf.*

class PdfFragment:Fragment() {
    private var _binding: FragmentPdfBinding? = null
    private val binding get() = _binding!!
    private lateinit var pdfView: PDFView

    companion object {
        const val PDF_SELECTION_CODE = 99
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentPdfBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun selectPdfFromStorage() {
        val browseStorage = Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type = "application/pdf"
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(
            Intent.createChooser(browseStorage, "Select PDF"), PDF_SELECTION_CODE
        )
    }

    private fun showPdfFromUri(uri: Uri?) {
        pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            val selectedPdfFromStorage = data.data
            showPdfFromUri(selectedPdfFromStorage)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        selectPdfFromStorage()
        super.onViewCreated(view, savedInstanceState)
    }
}

