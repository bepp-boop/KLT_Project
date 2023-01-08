package com.example.klt_project.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.klt_project.R
import kotlinx.android.synthetic.main.activity_mission.*


class MissionActivity : AppCompatActivity() {
    companion object {
        private const val PDF_SELECTION_CODE = 99
    }
//    private val progressBar: ProgressBar = findViewById(R.id.progressBar)


    /* val MISSION_MESSAGE = "com.example.klt_project.ui.home.mission.MESSAGE"
     val TO_MESSAGE = "com.example.klt_project.ui.home.to.MESSAGE"
     val FROM_MESSAGE = "com.example.klt_project.ui.home.from.MESSAGE"
     val LOAD_MESSAGE = "com.example.klt_project.ui.home.load.MESSAGE"
     val UNLOAD_MESSAGE = "com.example.klt_project.ui.home.unload.MESSAGE"*/
    //lateinit var pdfView: PdfDocument
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mission)
        /*val mMissionName: TextView = findViewById(R.id.mission_name)
        val missionMessage = intent.getStringExtra(MISSION_MESSAGE)
        val missionFrom = intent.getStringExtra(FROM_MESSAGE)
        val missionTo = intent.getStringExtra(TO_MESSAGE)
        val missionLoad = intent.getStringExtra(LOAD_MESSAGE)
        val missionUnload = intent.getStringExtra(UNLOAD_MESSAGE)
        val missionSentence =
            " Mission name: $missionMessage\n\n From: $missionFrom\n\n To: $missionTo\n\n Load: $missionLoad \n\n Unload: $missionUnload"
        mMissionName.text = missionSentence*/
        selectPdfFromStorage()
        Log.d("pdf"," OnCreate PDF")
    }
    private fun selectPdfFromStorage() {
        Toast.makeText(this, "selectPDF", Toast.LENGTH_LONG).show()
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
        progressBar.progress
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE && resultCode == RESULT_OK && data != null) {
            val selectedPdfFromStorage = data.data
            showPdfFromUri(selectedPdfFromStorage)
        }
    }
}