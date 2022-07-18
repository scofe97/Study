package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val number = br.readLine().toInt()
    val temp = sqrt(number.toDouble()).toInt()
    val dp = MutableList(number + 1) { i -> i }

    for (i in 4..number) {
        for (n in 1..temp) {
            if (i - n.toDouble().pow(2).toInt() >= 0) {
                dp[i] = min(dp[i], dp[i - n.toDouble().pow(2).toInt()] + 1)
            }else break
        }
    }

    val result = dp[number]
    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()

}