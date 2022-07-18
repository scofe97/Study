package com.example.viewmodel_livedata2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    var userName = MutableLiveData<String>()
    val userNameData : LiveData<String>
    get() = userName

    init{
        userName.value = "Frank"
    }
}