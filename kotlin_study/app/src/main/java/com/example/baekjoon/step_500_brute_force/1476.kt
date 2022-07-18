package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val max = 7981

    var list : MutableList<Int> = br.readLine().split(" ").map{it -> it.toInt()}.toMutableList()
    val list2 = MutableList<MutableList<Int>>(max){MutableList(3){0} }

    var num1 = 1
    var num2 = 1
    var num3 = 1

    for(i in 1..max){

        if(num1 == 16) num1 = 1
        if(num2 == 29) num2 = 1
        if(num3 == 20) num3 = 1

        list2[i] = mutableListOf(num1,num2,num3)

        if(list2[i] == list){
            bw.write(i.toString())
            break
        }

        num1++
        num2++
        num3++
    }

    bw.flush()
    bw.close()
    br.close()
}