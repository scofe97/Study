package com.example.calc2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val calc1 = Calc()
            val result = calc1.add(10,10)

            output1.text = "더하기 결과 : ${result}"
        }

        button2.setOnClickListener {

            // 인터페이스를 객체로 만드는 방법 (1회용)
            val calc1 = object: Calculator{
                override fun add(a:Int, b:Int):Int{
                    return a+b
                }
            }

            val calc2 = object:CalcAdapter(){
                override  fun add(a:Int, b:Int):Int{
                    return a + b
                }
            }


            // 이것이 setOnClickListener의 원형
            button3.setOnClickListener(object: View.OnClickListener{
                override fun onClick(v: View?) {
                    println("버튼 클릭됨")
                }
            })

            button3.setOnClickListener({ v -> println("버튼 클릭됨됨")})
            // v의 View는 인터페이스에 정의되어있어 생략이 가능하다

            button3.setOnClickListener{ v -> println("버튼 클릭됨됨")}
            // 람다를 밖으로 빼고, 소괄호를 지움(없으므로)

            button3.setOnClickListener{  println("버튼 클릭됨됨")}
            // v를 쓰지않으므로 지움
       }
    }

    override fun onDestroy() {
        // 이 화면이 사라지고 (메모리에서 사라지는) 그 때 사용하는 코드
        super.onDestroy()
    }
}