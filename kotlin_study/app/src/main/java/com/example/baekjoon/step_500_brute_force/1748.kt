package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val count = br.readLine().toInt()

    var tempCount = count
    var len = 0
    var result = 0

    while(tempCount > 0){
        tempCount /= 10
        len++
    }

    for(i in 1 until len){
        result += (10.0.pow(i-1)).toInt() * 9 * i
    }

    result += (count - (10.0.pow(len-1)).toInt() + 1) * len

    bw.write("$result")
    bw.flush()
    bw.close()
    br.close()

}