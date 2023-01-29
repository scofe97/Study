package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val count = br.readLine().toInt()
    val list = IntArray(count + 1) { 0 }

    list[0] = 1

    for (i in 2..count) {
        if (i % 2 == 0) {
            list[i] = list[i - 2] * 3

            for (j in i - 4 downTo 0 step 2) {
                list[i] += list[j] * 2
            }
        }

    }

    val result = list[count]

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()

}