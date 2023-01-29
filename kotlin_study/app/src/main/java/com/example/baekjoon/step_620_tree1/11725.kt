package com.example.baekjoon.step_620_tree1

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val list = MutableList<MutableList<Int>>(count){ mutableListOf()}
private val visited = IntArray(count){-1}
// 트리의 루트 1
// 트리 상에서 연결된 두 정점

private fun main(){

    repeat(count-1){
        val st = StringTokenizer(br.readLine())

        val v1 = st.nextToken().toInt()-1
        val v2 = st.nextToken().toInt()-1

        list[v1].add(v2)
        list[v2].add(v1)
    }

    visited[0] = 0
    dfs(0)

    for(i in 1 until count){
        bw.write("${visited[i]}\n")
    }

    bw.flush()
    bw.close()
    br.close()

}

private fun dfs(start:Int){
    for(i in list[start]){
        if(visited[i] == -1){
            visited[i] = start+1
            dfs(i)
        }
    }
}