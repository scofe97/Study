package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val k = br.readLine().toInt()

private var checkBipartite = true
private lateinit var list : MutableList<MutableList<Int>>
private lateinit var colors : IntArray

private const val RED = -1
private const val BLUE = 1

// 테스트 케이스의 개수 k
// 각 테스트 케이스의 1째줄 -> 그래프 정점의 개수 V와 간선의 개수 E
// E개의 줄에서 걸쳐 간선에 대한 정보가 주어짐

private fun main(){

    repeat(k){
        var st = StringTokenizer(br.readLine())
        val v = st.nextToken().toInt()
        val e = st.nextToken().toInt()

        list = MutableList<MutableList<Int>>(v){ mutableListOf()}
        colors = IntArray(v){0}
        checkBipartite = true

        repeat(e){
            st = StringTokenizer(br.readLine())
            val v1 = st.nextToken().toInt() -1
            val v2 = st.nextToken().toInt() -1

            list[v1].add(v2)
            list[v2].add(v1)
        }

        for(i in 0 until v){

            if(!checkBipartite) break

            if(colors[i] == 0){
                dfs(i, RED)
            }
        }

        if(checkBipartite) bw.write("YES\n") else bw.write("NO\n")

    }

    bw.flush()
    bw.close()
    br.close()

}

private fun dfs(idx: Int, color:Int){

    colors[idx] = color

    for(i in list[idx]){

        if(colors[i] == 0){
            dfs(i,color * -1)
        }

        else if(colors[i] + colors[idx] != 0){
            checkBipartite = false
            return
        }
    }

}