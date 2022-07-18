package com.example.funtion1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            println("버튼이 눌렸어요")
            show("안녕")
        }

        button2.setOnClickListener {
            val firstStr = input1.text.toString()
            val secondStr = input2.text.toString()

            val first = firstStr.toInt()
            val second = secondStr.toInt()

            val result = add(first, second)
            output1.text = "더하기 결과 : $result"
        }

        button3.setOnClickListener {
            val result = add(getFirst(), getSecond())
            output1.text = "더하기 결과 : $result"

            val result2 = add(a=10, b=10) // 파라미터 순서에 종속받지 않음
        }
    }

    fun sum(vararg inputs:Int): Int{  // 여러개의 인자가 붙음
        var output = 0
        for(num in inputs){
            output = output + num
        }
        return output
    }

    fun getFirst():Int{
        val firstStr = input1.text.toString()
        return firstStr.toInt()
    }
    fun getSecond():Int{
        val secondStr = input2.text.toString()
        return secondStr.toInt()
    }



    fun add(a:Int, b:Int = 0) : Int{
        return a + b
    }

    fun add2(a:Int, b:Int) : Int = a+b

    fun add3(a:Int, b:Int) = a + b // 알아서 추론해줌

    fun show(message: String){
        println("버튼이 눌렸어요 : $message")
    }


}