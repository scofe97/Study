package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    var num = 1

    var list : MutableList<Int> = br.readLine().split(" ").map{it -> it.toInt()}.toMutableList()


    while(true){

        if((num-list[0])%15 == 0 && (num-list[1])%28==0 && (num-list[2])%19 == 0) break
        else num ++
    }

    bw.write(num.toString())
    bw.flush()
    bw.close()
    br.close()
}