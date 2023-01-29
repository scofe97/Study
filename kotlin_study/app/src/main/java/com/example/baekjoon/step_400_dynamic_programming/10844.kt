package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val number = br.readLine().toInt()
    val list = Array(number + 1) { IntArray(10) { _ -> 1 } }
    var result = 0

    for (i in 2..number) {
        for (n in 0..9) {
            when (n) {
                0 -> list[i][n] = list[i - 1][n + 1]
                in 1..8 -> list[i][n] = (list[i - 1][n + 1] + list[i - 1][n - 1]) % 1000000000
                9 -> list[i][n] = list[i - 1][n - 1]
            }
        }
    }

    for (i in 1..9) {
        result += list[number][i]
        result %= 1000000000
    }

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()

}