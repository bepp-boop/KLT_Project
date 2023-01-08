package com.example.klt_project.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.example.klt_project.R
import com.example.klt_project.utils.FileUtils.getPdfUrl
import com.example.klt_project.utils.FileUtils.getRootDirPath
import com.github.barteksc.pdfviewer.util.FileUtils
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_mission.*
import java.io.File


class MissionActivity : AppCompatActivity() {
   // private val database = Firebase.database("https://klt-prototype-default-rtdb.europe-west1.firebasedatabase.app/")
   // private val myRef = database.getReference("Mission")
    companion object {
        private const val PDF_SELECTION_CODE = 99
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission)

        progressBar.visibility = View.VISIBLE
        val fileName = "myFile.pdf"
        downloadPdfFromInternet(
            getPdfUrl(),
            getRootDirPath(this),
            fileName
        )
        Log.d("pdf"," OnCreate PDF")
    }
    private fun showPdfFromFile(file: File) {
        pdfView.fromFile(file)
            .password(null)
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .onPageError { page, _ ->
                Toast.makeText(
                    this@MissionActivity,
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
                    Toast.makeText(this@MissionActivity, "downloadComplete", Toast.LENGTH_LONG)
                        .show()
                    val downloadedFile = File(dirPath, fileName)
                    progressBar.visibility = View.GONE
                    showPdfFromFile(downloadedFile)
                }

                override fun onError(error: Error?) {
                    Toast.makeText(
                        this@MissionActivity,
                        "Error in downloading file : $error",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })
    }
}