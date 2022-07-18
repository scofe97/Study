package com.anushka.viewmodeldemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.anushka.viewmodeldemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // ViewModel 을 가져온다
    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // ViewModel 생성 ( this -> 뷰 컨트롤러(현재 액티비티) )
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.countText.text = viewModel.getCurrentCount().toString()
        binding.button.setOnClickListener {
            binding.countText.text = viewModel.getUpdateCount().toString()
        }
    }
}
