package com.example.kotlin_study.function

object MyFunction{
    operator fun invoke(str: String) : String{
        return str.toUpperCase()
    }
}

fun main(){
    MyFunction.invoke("hello") // HELLO

    MyFunction("hello") // HELLO
}