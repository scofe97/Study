package com.example.person2

import android.widget.TextView

open class Person(open var name:String?) {
    var age:Int? = null
    var address:String? = null

    constructor(name:String?, age:Int?, address:String?):this(name){
        println("생성자 호출됨")

        this.age = age
        this.address = address
    }

    open fun walk(output:TextView){ //ui부분 역시 전달해줄수 있음
        println("걷는다.")
        output.text = "사람이 걷는다"
    }
}