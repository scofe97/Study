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
private val start = (st.nextToken().toInt()) -1

private val list = MutableList<MutableList<Int>>(vertex){ mutableListOf()}
private val visitedDfs = BooleanArray(vertex)
private val visitedBfs = BooleanArray(vertex)


private fun main(){

    repeat(edge){
        val st = StringTokenizer(br.readLine())
        val v1 = (st.nextToken().toInt())-1
        val v2 = (st.nextToken().toInt())-1
        list[v1].add(v2)
        list[v2].add(v1)
    }

    repeat(vertex){ idx ->
        list[idx].sort()
    }

    dfs(start)
    bw.write("\n")

    bfs()
    bw.flush()
    bw.close()
    br.close()

}

private fun dfs(start:Int){
    bw.write("${start+1} ")
    visitedDfs[start] = true

    for(i in list[start]){
        if(!visitedDfs[i]) dfs(i)
    }
}

private fun bfs(){
    val queue : LinkedList<Int> = LinkedList()

    queue.add(start)
    visitedBfs[start] = true

    while(queue.isNotEmpty()){
        val temp = queue.poll()!!
        bw.write("${temp+1} ")

        for(i in list[temp]){
            if(!visitedBfs[i]){
                visitedBfs[i] = true
                queue.add(i)
            }
        }
    }
}
