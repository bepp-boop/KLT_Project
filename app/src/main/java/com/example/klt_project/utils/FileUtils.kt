package com.example.klt_project.utils


import android.os.Environment
import androidx.core.content.ContextCompat
import com.example.klt_project.ui.home.ui.PdfFragment
import java.io.File

object FileUtils {

    fun getPdfUrl(): String {
        return "https://firebasestorage.googleapis.com/v0/b/klt-prototype.appspot.com/o/KLT221201.pdf?alt=media&token=ef8c3893-ec9f-4fd6-b751-b3b3a2dc0f75"
    }
    fun getRootDirPath(context: PdfFragment): String {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val file: File = ContextCompat.getExternalFilesDirs(
                context.requireContext(),
                null
            )[0]
            file.absolutePath
        } else {
            context.requireContext().filesDir.absolutePath
        }
    }
}