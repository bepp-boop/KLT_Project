package com.example.klt_project.ui.home.ui

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.example.klt_project.databinding.FragmentPdfBinding
import com.example.klt_project.utils.FileUtils
import com.github.barteksc.pdfviewer.PDFView
import java.io.File

class PdfFragment:Fragment() {
    private var _binding: FragmentPdfBinding? = null
    private val binding get() = _binding!!
    private lateinit var pdfView: PDFView

    companion object {
        private const val PDF_SELECTION_CODE = 99
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentPdfBinding.inflate(inflater, container, false)
        //progressBar.visibility = View.VISIBLE

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        pdfView = binding.pdfView
        val fileName = "myFile.pdf"
        downloadPdfFromInternet(
            FileUtils.getPdfUrl(),
            FileUtils.getRootDirPath(this),
            fileName
        )
    }

//    override fun setMenuVisibility(menuVisible: Boolean) {
//        super.setMenuVisibility(menuVisible)
//        pdfView = binding.pdfView
//        val fileName = "myFile.pdf"
//        downloadPdfFromInternet(
//            FileUtils.getPdfUrl(),
//            FileUtils.getRootDirPath(this),
//            fileName
//        )
//    }

    private fun showPdfFromFile(file: File) {
        pdfView.fromFile(file)
            .password(null)
            .defaultPage(0)
            .enableSwipe(false)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .onPageError { page, _ ->
                Toast.makeText(
                    context,
                    "Error at page: $page", Toast.LENGTH_LONG
                ).show()
            }
            .load()
    }

    private fun downloadPdfFromInternet(url: String, dirPath: String, fileName: String) {
        PRDownloader.download(
            url,
            dirPath,
            fileName
        ).build()
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    //Toast.makeText(context, "downloadComplete", Toast.LENGTH_LONG)
                        //.show()
                    val downloadedFile = File(dirPath, fileName)
                    //progressBar.visibility = View.GONE
                    showPdfFromFile(downloadedFile)
                }

                override fun onError(error: Error?) {
                    Toast.makeText(
                        context,
                        "Error in downloading file : $error",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })
    }
}

