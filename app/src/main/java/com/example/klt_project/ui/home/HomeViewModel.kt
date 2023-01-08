package com.example.klt_project.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.klt_project.DataList


class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = DataList.missionsID.toString()
    }
    val text: LiveData<String> = _text
}