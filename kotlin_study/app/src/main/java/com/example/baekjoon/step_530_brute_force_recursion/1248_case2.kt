package com.example.baekjoon.step_530_brute_force_recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.system.exitProcess


private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val size = br.readLine().toInt()
private val graph = IntArray(size)

fun main() {
    var string = br.readLine()

    val input = Array(size) { CharArray(size) }
    for (i in 0 until size) {
        for (j in i until size) {
            input[i][j] = string.first()
            string = string.substring(1)
        }
    }

    fun possible(depth: Int): Boolean {
        var sum = 0

        for (i in depth downTo 0) {
            sum += graph[i]

            if (input[i][depth] == '+' && sum <= 0) return false
            if (input[i][depth] == '-' && sum >= 0) return false
            if (input[i][depth] == '0' && sum != 0) return false
        }
        return true
    }

    fun dfs(depth: Int) {
        if (depth == size) {
            for (i in graph) {
                bw.write("$i ")
            }
            bw.flush()
            bw.close()
            br.close()
            exitProcess(0)
        }

        for (i in -10..10) {
            graph[depth] = i
            if (possible(depth)) dfs(depth + 1)
        }
    }

    dfs(0)

}





