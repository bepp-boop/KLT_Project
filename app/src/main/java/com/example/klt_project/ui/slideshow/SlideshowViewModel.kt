package com.example.klt_project.ui.slideshow

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klt_project.DataList

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<HashMap<String, Array<Int>?>>().apply {
        value = DataList.timeElapsed
    }
    val text: LiveData<HashMap<String, Array<Int>?>> = _text

    private val _note = MutableLiveData<String>().apply {
        value = DataList.note
    }
    val note: LiveData<String> = _note
}