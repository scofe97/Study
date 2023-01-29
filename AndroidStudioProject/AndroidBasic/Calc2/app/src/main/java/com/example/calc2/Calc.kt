package com.example.calc2

class Calc : Calculator {

    // override var name:String = "이름"
    override fun add(a:Int, b:Int) = a + b // 인터페이스 함수 정의, 구현

    override fun subrtact(a: Int, b: Int): Int = a-b
    // fun subtract(a:Int, b:Int) = a - b
    fun multiply(a:Int, b:Int) = a * b
    fun divide(a:Int, b:Int) = a / b

}