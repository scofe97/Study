package com.example.baekjoon.step_530_brute_force_recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val list = arrayOf(1, 2, 3)

fun main() {

    val count = br.readLine().toInt()

    repeat(count) {
        val number = br.readLine().toInt()
        var result = 0

        fun dfs(number: Int, sum: Int) {
            when {
                sum > number -> return
                sum == number -> {
                    result++
                    return
                }
                else -> {
                    for (i in list) {
                        dfs(number, sum + i)
                    }
                }
            }
        }

        dfs(number, 0)
        bw.write("$result\n")

    }
    bw.flush()
    bw.close()
    br.close()
}

