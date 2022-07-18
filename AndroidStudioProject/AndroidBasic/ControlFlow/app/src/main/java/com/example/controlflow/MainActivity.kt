package com.example.controlflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val a = 10
            val b = 10
            var max:Int

            if(a > b){
                max = a
            }else{
                max = b
            }

            max = if(a > b) a else b

            // when문
            when(max){
                in 1..9 ->{
                    println("max가  1~9입니다")
                }
                10,15 -> {
                    println("max가 10이거나 15입니다")
               }
                20 -> {
                    println("max가 20입니다")
                }
                else ->{
                    println("몰라레후 ")
                }
            }
        }
    }
}