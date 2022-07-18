package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 1000000009

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val count = br.readLine().toInt()
    val list = Array(100001) { IntArray(4) { _ -> 0 } }

    list[1][1] = 1
    list[2][2] = 1

    list[3][1] = 1
    list[3][2] = 1
    list[3][3] = 1


    repeat(count) {
        val number = br.readLine().toInt()

        for (i in 4..number) {
            if (list[i][1] != 0 || list[i][2] != 0 || list[i][3] != 0) continue

            list[i][1] = (list[i - 1][2] + list[i - 1][3]) % MOD
            list[i][2] = (list[i - 2][1] + list[i - 2][3]) % MOD
            list[i][3] = (list[i - 3][1] + list[i - 3][2]) % MOD

        }
        val result = ((list[number][1] + list[number][2]) % MOD + list[number][3]) % MOD
        bw.write("$result\n")
    }

    bw.flush()
    bw.close()
    br.close()
}