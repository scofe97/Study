package com.example.hello

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 이곳에 선언하는 변수는 무조건 초기화 해야함 (클래스 멤버)
    var name : String? = null
    lateinit var mobile: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            //var nameLength = name!!.length // 바로 쓸수없음 null 의 랩을 벗겨줘야 사용가능
            //var nameLength = name?.length // null 이 아닌경우만 사용하겠다 (null을 자동으로 벗김)
            var nameLength = name?.length ?:0 // 만약 null인경우 0을 기본값으로 넣겠다

            name = nameinput.text.toString()
            mobile = mobileinput.text.toString()

            Toast.makeText(applicationContext, "이름 : $name, 전화번호 : $mobile", Toast.LENGTH_LONG).show()

            println("안녕 : $name")
            println("""
                안녕하세요
            """.trimIndent()) // 여러줄
            //$<변수이름> , $(표현식)

            var name2 : String? // 널 허용용
            name!! // 언래핑
        }


    }
}