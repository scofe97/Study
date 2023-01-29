package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.system.exitProcess

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

private val st = StringTokenizer(br.readLine())
private val count = st.nextToken().toInt()
private val relation = st.nextToken().toInt()

private val list = MutableList<MutableList<Int>>(count){ mutableListOf()}
private val visited = BooleanArray(count)


private fun main() {
    repeat(relation) {
        val st = StringTokenizer(br.readLine())
        val f1 = st.nextToken().toInt()
        val f2 = st.nextToken().toInt()
        list[f1].add(f2)
        list[f2].add(f1)
    }

    for(i in 0 until count){
        visited[i] = true
        dfs(i,0)
        visited[i] = false
    }

    bw.write("0")
    bw.flush()
    bw.close()
    br.close()

}

private fun dfs(idx: Int, depth: Int) {

    if (depth == 4) {
        bw.write("1")
        bw.flush()
        bw.close()
        br.close()
        exitProcess(0)
    }

    for (i in 0 until list[idx].size) {

        val next = list[idx][i]

        if (!visited[next]) {
            visited[next] = true
            dfs(next, depth + 1)
            visited[next] = false
        }
    }
}
