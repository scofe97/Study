package com.example.ch3_4

class test {

    private fun test(a : Int, b:Int){
    }

    private fun text (c:Int){

    }

    val aa : (Int,Int)->Unit = { i: Int, i1: Int -> test(i,i1)}
    val bb : (Int) -> Unit = {text(it)}
}