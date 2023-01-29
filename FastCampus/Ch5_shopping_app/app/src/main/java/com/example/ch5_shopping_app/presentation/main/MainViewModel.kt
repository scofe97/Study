package com.example.ch5_shopping_app.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ch5_shopping_app.databinding.ActivityMainBinding
import com.example.ch5_shopping_app.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MainViewModel : BaseViewModel() {
    
    override fun fetchData(): Job = Job()

    private var _mainStateLiveData = MutableLiveData<MainState>()
    val mainStateLiveData : LiveData<MainState> = _mainStateLiveData

    fun refreshOrderList() = viewModelScope.launch {
        _mainStateLiveData.postValue(MainState.RefreshOrderList)
    }
}