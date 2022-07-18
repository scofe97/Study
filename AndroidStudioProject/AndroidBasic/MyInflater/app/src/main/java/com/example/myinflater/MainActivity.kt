package com.example.myinflater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            // 자신이 직접 메모리에 올려줘야함
            // 내가 직접 container에 part1을 넣음
            layoutInflater.inflate(R.layout.part1, container, true)
        }
    }
}