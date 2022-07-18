package com.example.baekjoon.step_540_brute_force_bitmask

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val st = StringTokenizer(br.readLine())
private val listSize = st.nextToken().toInt()
private val listSum = st.nextToken().toInt()
private val list = br.readLine().split(" ").map { it -> it.toInt() }.toIntArray()
private val visited = BooleanArray(listSize)
private val graph = IntArray(listSize)
private var temp = 0
private var result = 0
fun main() {

    dfs(0, 0, list)

    if(listSum == 0) result--

    bw.write("$result")
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(idx:Int, sum:Int, arr:IntArray) {
    if(idx==listSize) {
        if(sum==listSum)
            result++
        return
    }

    dfs( idx+1, sum, arr)
    dfs(idx+1, sum+arr[idx].toInt(), arr)
}