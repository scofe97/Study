package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val number = br.readLine().toInt()
    var count = 0
    var num = 666

    while(number != count){

        if(num.toString().contains("666")) count++

        if(count == number ) break

        num++
    }

    bw.write("$num")
    bw.flush()
    bw.close()
    br.close()

}