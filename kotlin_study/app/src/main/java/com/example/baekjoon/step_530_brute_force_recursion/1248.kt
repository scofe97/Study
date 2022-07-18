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
private var s = MutableList(size) { MutableList<String>(size) { "" } }

private val graph = mutableListOf<Int>()

fun main() {
    val st = StringTokenizer(br.readLine())
    val temp = st.nextToken().chunked(1).toMutableList()

    for (i in 0 until size) {
        for (j in i until size) {
            s[i][j] = temp.removeAt(0)
        }
    }
    dfs(0)

}

private fun dfs(depth: Int) {
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
        graph.add(i)
        if (possible(depth)) dfs(depth + 1)
        graph.removeLast()
    }
}



private fun possible(depth: Int): Boolean {
    var sum = 0

    for (i in depth downTo 0) {
        sum += graph[i]

        if (s[i][depth] == "+" && sum <= 0) return false
        if (s[i][depth] == "-" && sum >= 0) return false
        if (s[i][depth] == "0" && sum != 0) return false
    }
    return true
}