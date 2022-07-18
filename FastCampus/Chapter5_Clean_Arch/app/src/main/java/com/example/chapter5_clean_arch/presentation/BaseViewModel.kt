package com.example.chapter5_clean_arch.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

internal  abstract class BaseViewModel : ViewModel() {

    abstract fun fetchData() : Job
}