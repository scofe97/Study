package com.example.baekjoon.step_520_brute_force_permutation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs
import kotlin.math.max

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val graph = IntArray(count)
private val visited = BooleanArray(count){false}
private val list = IntArray(count)
private var max = 0

fun main(){

    val st = StringTokenizer(br.readLine())
    repeat(count){ idx ->
        list[idx] = st.nextToken().toInt()
    }
    dfs(0)
    bw.write(max.toString())
    bw.flush()
    bw.close()
    br.close()


}

private fun dfs(step: Int){
    if(step == count){
        var temp = 0
        for(i in 0 until count-1){
            temp += abs(graph[i] - graph[i+1])
        }
        max = max(max, temp)
    }

    for(i in 0 until count){
        if(!visited[i]){
            visited[i] = true
            graph[step] = list[i]
            dfs(step+1)
            visited[i] = false
        }
    }
}