package com.example.baekjoon.step_400_dynamic_programming


import kotlin.math.min


fun main() {
    val n = readLine()!!.toInt()
    val dp = IntArray(n+2)

    dp[1] = 1
    dp[2] = 2

    for (i in 3..n){
        var minValue = Integer.MAX_VALUE
        for (j in 1 until i){
            if (j * j > i) break

            minValue = min(minValue, 1 + dp[i - j * j])
        }

        dp[i] = minValue
    }

    println(dp[n])

}