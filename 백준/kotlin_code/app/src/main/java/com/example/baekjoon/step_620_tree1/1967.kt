package com.example.baekjoon.step_620_tree1

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val list = MutableList<MutableList<Pair<Int,Int>>>(count){ mutableListOf()}
private lateinit var v : BooleanArray

private var max = 0
private var destination = 0

private fun main(){

    repeat(count-1){
        val st = StringTokenizer(br.readLine())

        val data = st.nextToken().toInt()-1

        while(st.hasMoreTokens()){
            val v = st.nextToken().toInt()-1
            val weight = st.nextToken().toInt()
            list[data].add(Pair(v,weight))
            list[v].add(Pair(data,weight))
        }
    }

    v = BooleanArray(count)
    v[0] = true
    dfs(0,0,v)

    v = BooleanArray(count)
    v[destination] = true
    dfs(destination,0,v)


    bw.write("$max")
    bw.flush()
    bw.close()
    br.close()

}

private fun dfs(start:Int,sum:Int, visited:BooleanArray){
    for(i in list[start]){

        val v = i.first
        val weight = i.second

        if(!visited[v]){
            if(max < sum+weight){
                max = sum+weight
                destination = v
            }
            visited[v] = true
            dfs(v, sum+weight,visited)
        }
    }
}