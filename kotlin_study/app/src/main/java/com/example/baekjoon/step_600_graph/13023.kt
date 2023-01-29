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
private val list = Array<BooleanArray>(count) { BooleanArray(count) }
private val visited = BooleanArray(count)


private fun main() {
    repeat(relation) {
        val st = StringTokenizer(br.readLine())
        val f1 = st.nextToken().toInt()
        val f2 = st.nextToken().toInt()
        list[f1][f2] = true
        list[f2][f1] = true
    }

    for(i in 0 until count){
        dfs(i,0)
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

    for (i in 0 until count) {
        if (list[idx][i] && !visited[i]) {
            visited[idx] = true
            dfs(i, depth + 1)
            visited[idx] = false
        }
    }
}
