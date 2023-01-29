package com.example.basic3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basic3.constants.mBonus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var first:Int = 0
    var second:Int = 0

    companion object{
        // 변수나 함수를 담을수 있음
        // 여기서 클래스에서 바로 접근이 가능
        const val BOUNS = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton.setOnClickListener {
            val firstStr = input1.text.toString()
            val secondStr = input2.text.toString()

            first = firstStr.toInt()
            second = secondStr.toInt()

            val result = first + second + MainActivity.BOUNS + mBonus
            output1.text = "결과 : $result"

            if( result == 20 ){
                output1.text = "더하기 결과가 20입니다"
            }

            if( firstStr == "10"){
                output1.append("\n첫번째 입력된 값이 10입니다.")
            }

            val input1 : Any = "안녕" // Any는 어떠한 값도 들어감감 하지만 문자가 들어갔으니 크기는 문자열로 정해짐
            val input2 : Any = 10

            if( input1 is String ){
                val output2:String = input1 as String // String 으로 변환
                output1.text = "input1은 문자열 자료형입니다."

            }

            val output3:String? = input1 as String?

        }
    }
}