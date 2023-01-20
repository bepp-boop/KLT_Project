package com.example.klt_project.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klt_project.DataList

class SlideshowViewModel : ViewModel() {

    private val _note = MutableLiveData<String>().apply {
        value = DataList.note
    }
    val note: LiveData<String> = _note

    private val _pressed = MutableLiveData<ArrayList<Int>>().apply {
        value = DataList.buttonArray
    }
    val pressed: LiveData<ArrayList<Int>> = _pressed

    private val _saveTime = MutableLiveData<ArrayList<String>>().apply {
        value = DataList.saveTime
    }
    val saveTime: LiveData<ArrayList<String>> = _saveTime

}