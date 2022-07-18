package com.example.baekjoon.step_520_brute_force_permutation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val size = br.readLine().toInt()
private val visited = BooleanArray(size){false}
private val graph = IntArray(size)

private fun main() {

    dfs(0)
    bw.flush()
    bw.close()
    br.close()

}

private fun dfs(step:Int){

    if(step == size){
        for(i in graph){
            bw.write("$i ")
        }
        bw.write("\n")
    }

    for(i in 0 until size){
        if(!visited[i]){
            visited[i] = true
            graph[step] = i+1
            dfs(step+1)
            visited[i] = false
        }
    }
}