package com.example.myactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        backButton.setOnClickListener {
            val backIntent = Intent()
            backIntent.putExtra("data","홍길동")
            setResult(201,backIntent)

            finish() // 엑티비티를 종료해라

        }
        // showIntent값이다
        if (intent != null){
            processIntent(intent)
        }
    }

    fun processIntent(passedIntent: Intent){
        // 타입별로 extra가 여러개개
       val mobile = passedIntent.getStringExtra("mobile")
        if (mobile != null){
            showToast("전달받은 값 : $mobile")
        }

        // 코드블록의 코드 (아직은 몰라도됨)
        //mobile?.run{  showToast("전달받은 값 : $mobile") }

    }

    fun showToast(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_LONG).show()
    }
}