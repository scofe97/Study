package com.example.calc1

class Person(var name:String?) {


    var age:Int? = null
    lateinit var address:String

    init{
        println("생성자가 호출됨")

    }
}