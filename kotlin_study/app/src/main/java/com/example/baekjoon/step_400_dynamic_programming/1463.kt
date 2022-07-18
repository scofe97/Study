package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))


    val number = br.readLine().toInt()
    val dp = MutableList(number+110){ _ -> 0}

    for(i in 2..number){
        dp[i] = dp[i-1] + 1
        if (i % 2 == 0) dp[i] = min(dp[i], dp[i / 2] + 1)
        if (i % 3 == 0) dp[i] = min(dp[i], dp[i / 3] + 1)
    }

    bw.write(dp[number].toString())
    bw.flush()
    bw.close()
    br.close()

}

