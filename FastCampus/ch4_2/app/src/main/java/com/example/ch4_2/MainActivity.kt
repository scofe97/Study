package com.example.ch4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                // 프래그먼트 생성
                // newInstance 생성이유 (후에 인자값 받는 코드작성이 수월하기 때문)
                .replace((R.id.fragmentContainer), PlayerFragment.newInstance())
                .commit()
    }
}