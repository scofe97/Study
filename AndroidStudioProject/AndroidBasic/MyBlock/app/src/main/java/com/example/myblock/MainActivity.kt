package com.example.myblock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val person1 = Person()
            person1?.name = "홍길동1"
            person1?.age = 20
            person1?.mobile = "010-1000-1000"
            // ?.이 반복됨
            // 개체의 속성을 초기화 하기위한 코드

            // 코드블록 apply 이용한 초기화 코드 줄이기
            val person2 = Person().apply {
                this.name = "홍길동1"
                age = 20
                mobile = "010-1000-1000"
            }

            // with 코드블록
            showToast("이름 : ${person2?.name}")
            showToast("나이 : ${person2?.age}")
            showToast("전화번호 : ${person2?.mobile}")

            with(person2!!){
                showToast("이름 : $name")
                showToast("나이 : $age")
                showToast("전화번호 : $mobile")
            }

            // 단축 let
            if(person2 != null){
                showToast("이름 : ${person2.name}")
                showToast("나이 : ${person2.age}")
                showToast("전화번호 : ${person2.mobile}")
            }else{
                showToast("사람 객체가 널이다")
            }

            person2?.let{
                showToast("이름 : ${it.name}")
                showToast("나이 : ${it.age}")
                showToast("전화번호 : ${it.mobile}")
            }?: run{
                showToast("사람 객체가 널이다.")
            }

            person2?.run {
                showToast("이름 : $name")
                showToast("나이 : $age")
                showToast("전화번호 : $mobile")
            }?: run{
                showToast("사람 객체가 널이다.")
            }
            // 무엇이 더 좋다고 설명할수 없지만 간결하게 만드는 목적에는 밑이 좋음음

            var person3 = Person()
            if( person3.name == null){
                showToast("사람 객체의 name 속성이 널이다.")
            }
            if( person3.age == null){
                showToast("사람 객체의 age 속성이 널이다.")
            }

            val person4 = Person()
            // 반환도 같이 해줌
            // 이미 만들어져있는 객체를 바꿈
           val person5 = person4.also{
                if( it.name == null){
                    showToast("사람 객체의 name 속성이 널이다.")
                }
                it.name = "홍길동4"
                if( it.age == null){
                    showToast("사람 객체의 age 속성이 널이다.")
                }
                it.age = 21
            }



       }
    }

    fun showToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}