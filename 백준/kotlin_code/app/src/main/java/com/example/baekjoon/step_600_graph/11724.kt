package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

private val st = StringTokenizer(br.readLine())
private val vertex = st.nextToken().toInt()
private val edge = st.nextToken().toInt()

private val list = MutableList<MutableList<Int>>(vertex) { mutableListOf() }
private val visited = BooleanArray(vertex)

private var result = 0

private fun main() {

    repeat(edge) {
        val st = StringTokenizer(br.readLine())
        val v1 = (st.nextToken().toInt()) - 1
        val v2 = (st.nextToken().toInt()) - 1
        list[v1].add(v2)
        list[v2].add(v1)
    }

    for(i in 0 until vertex){
        if(!visited[i]){
            dfs(i)
            result++
        }
    }

    bw.write("$result")
    bw.flush()
    bw.close()
    br.close()
}

private fun dfs(start: Int) {
    visited[start] = true
    for(i in list[start]){
        if(!visited[i]){
            dfs(i)
        }
    }
}