package com.anushka.viewmodeldemo2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal : Int) : ViewModel() {
     private var total = MutableLiveData<Int>()
     val totalData : LiveData<Int> get() = total
    var addNumber = MutableLiveData<String>()

    init {
        total.value = startingTotal
    }

    fun setTotal(){
        val inputInt = addNumber.value!!.toInt()
        total.value =(total.value)?.plus(inputInt)
    }
}