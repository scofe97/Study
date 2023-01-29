package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){


    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val number = br.readLine().toInt()
    val list = Array(number + 1) { LongArray(2) { _ -> 1 } }
    var result = 0L

    for(i in 2..number){
        list[i][1] = list[i-1][0]
        list[i][0] = list[i-1][0] + list[i-1][1]
    }

    result = list[number][1]

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()

}