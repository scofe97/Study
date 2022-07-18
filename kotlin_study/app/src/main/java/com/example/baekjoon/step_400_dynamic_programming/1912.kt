package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))


    val count = br.readLine().toInt()
    val list = br.readLine().split(" ").map { it.toInt() }.toList()
    val dp = MutableList(count){0}

    for( i in 0 until count){
        dp[i] = list[i]
        if(i == 0 ) continue

        dp[i] = max(dp[i-1] + dp[i], dp[i] )
    }

    val result = dp.maxOrNull()
    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()

}