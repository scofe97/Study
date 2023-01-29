package com.example.baekjoon.step_530_brute_force_recursion

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val list = Array<Pair<Int, Int>>(count) { Pair(0, 0) }
private var result = 0


fun main() {
    repeat(count) { idx ->
        val st = StringTokenizer(br.readLine())
        list[idx] = Pair(st.nextToken().toInt(), st.nextToken().toInt())
    }


    fun dfs(day: Int, profits: Int) {

        if (day == count) {
            result = max(result, profits)
            return
        }

        if (day > count) return

        dfs(day + 1, profits)
        dfs(day + list[day].component1(), profits + list[day].component2())
    }

    dfs(0,0)


    bw.write("$result")
    bw.flush()
    bw.close()
    br.close()
}