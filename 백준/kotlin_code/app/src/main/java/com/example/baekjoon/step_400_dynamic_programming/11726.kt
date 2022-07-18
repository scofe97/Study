package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){


    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))


    val number = br.readLine().toInt()
    val dp = MutableList<Int>(number+1){ _ ->0}

    for(i in 0..number){

        if(i < 2){
            dp[i] = 1
            continue
        }

        dp[i] = (dp[i-1] + dp[i-2]) % 10007
    }

    bw.write(dp[number].toString())
    bw.flush()
    bw.close()
    br.close()
}
