package com.example.calc1

import java.net.Inet4Address

class Person2 {

    var name:String? = null
    var age:Int? = null
    lateinit var address:String

    constructor(){
        println("첫번째 생성자 호출됨")
    }
    constructor(name:String?){
        println("두번째 생성자 호출됨")

        this.name = name // this 자기자신을 의미함 (이 클래스의 name)
    }
    constructor(name:String?, age:Int?, address: String){
        println("세번째 생성자 호출됨")

        this.name = name
        this.age = age
        this.address = address
    }
}