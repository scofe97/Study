package com.anushka.viewmodeldemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var count = MutableLiveData<Int>(0)
    val countData : LiveData<Int>
    get() = count

    init{
        count.value = 0
    }

    fun getUpdatedCount(){
        count.value = (count.value)?.plus(1)
    }
}