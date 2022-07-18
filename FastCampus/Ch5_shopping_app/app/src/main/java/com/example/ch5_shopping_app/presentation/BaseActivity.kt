package com.example.ch5_shopping_app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.ch5_shopping_app.R
import kotlinx.coroutines.Job

internal abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding>: AppCompatActivity() {

    abstract val viewModel: VM

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    private lateinit var fetchJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        fetchJob = viewModel.fetchData()
        observeData()
    }

    abstract fun observeData()

    override fun onDestroy() {
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
        super.onDestroy()
    }

}