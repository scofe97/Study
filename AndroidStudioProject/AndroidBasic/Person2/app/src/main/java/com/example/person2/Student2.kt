package com.example.person2

class Student2 : Person {

    constructor(alias:String? , age:Int?, address:String?):super(alias){
        // super는 부모를 의미함
        println("Student2 생성자 호출됨")
    }
}