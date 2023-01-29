package com.example.person1

class Student (var alias:String) : Person(alias){
    // 상속을 받을 때는 :으로 부모상속 넣어줌
    // 부모의 기본생성자 형태를 넣어줌

    constructor(alias: String, age:Int, address:String):this(alias){
        println("Student 생성자 호출됨")

        this.age = age
        this.address = address
    }
}