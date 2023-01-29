package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val st = StringTokenizer(br.readLine())

    val number = st.nextToken().toInt()
    val count = st.nextToken().toInt()


    val dp = MutableList(number + 1) { MutableList(count + 1) { 0 } }

    for(i in 0..count){
        dp[0][i] = 1
    }

    for(i in 0..number){
        dp[i][0] = 0
        dp[i][1] = 1
    }


    for (i in 1..number) {
        for (j in 2..count) {
            for(k in 0..i){
                dp[i][j] += dp[i-k][j-1]
                dp[i][j] %= 1000000000
            }
        }
    }

   val result = dp[number][count]
    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()

}