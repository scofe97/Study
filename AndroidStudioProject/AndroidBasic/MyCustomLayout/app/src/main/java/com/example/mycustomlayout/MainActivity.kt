package com.example.mycustomlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.part1.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            view.output1.setText("홍길동1")
            view.output2.setText("010-1000-1000")
            view.imageView.setImageResource(R.drawable.ic_launcher_foreground)
            // 카드뷰 제작 언급 (책관련)
        }
    }
}