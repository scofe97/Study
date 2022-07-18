package com.example.person2

import android.widget.TextView

class Student (override var name:String?) : Person(name){
    // 상속을 받을 때는 :으로 부모상속 넣어줌
    // 부모의 기본생성자 형태를 넣어줌

    constructor(name: String, age:Int, address:String):this(name){
        println("Student 생성자 호출됨")

        this.age = age
        this.address = address
    }

    override fun walk(output: TextView) {
        output.text = "학생이 걷는다"
    }
}