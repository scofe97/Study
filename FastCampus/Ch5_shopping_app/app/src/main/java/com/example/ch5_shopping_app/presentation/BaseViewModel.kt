package com.example.ch5_shopping_app.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

internal abstract class BaseViewModel: ViewModel() {

    abstract fun fetchData(): Job

}