package com.example.calc1

class Person3(var nmae:String?) {

    var name:String? = null
    var age:Int? = null
    lateinit var address:String

    // 기본생성자가 있어 직접생성자에 기본생성자를 처음 만들어줘야 함
    // this(name) 기본생성자가 호출됨
    constructor(name:String?, age:Int?):this(name){

    }
}