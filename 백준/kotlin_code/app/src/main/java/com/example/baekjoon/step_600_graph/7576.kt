package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max


private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val st = StringTokenizer(br.readLine())

private val m = st.nextToken().toInt()
private val n = st.nextToken().toInt()
private val tomato = Array(n){IntArray(m)}
private val visited = Array(n){BooleanArray(m)}
private val tomatoOn = mutableListOf<Pair<Int,Int>>()

private fun main(){

    solution()
    bw.flush()
    bw.close()
    br.close()

}

private fun solution(){
    var result = 0

    repeat(n){ idx ->
        tomato[idx] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    for(i in 0 until n){
        for(j in 0 until m){
            if(tomato[i][j] == 1  && !visited[i][j]){
                tomatoOn.add(Pair(i,j))
            }
        }
    }

    bfs(tomatoOn)
    for(i in tomato){
        for(j in i){
            if(j == 0){
                bw.write("-1")
                return
            }
            result = max(result, j)
        }
    }

    bw.write("${result-1}")
}

private fun bfs(t : MutableList<Pair<Int,Int>>){
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)


    val queue = LinkedList<Pair<Int,Int>>()
    for(i in t){
        visited[i.first][i.second] = true
        queue.add(i)
    }

    while(queue.isNotEmpty()){
        val temp = queue.poll()!!

        for(i in 0 until 4){
            val y2 = temp.first + dy[i]
            val x2 = temp.second + dx[i]

            if(y2 !in 0 until n || x2 !in 0 until m) continue
            if(tomato[y2][x2] != 0 || visited[y2][x2]) continue

            visited[y2][x2] = true
            queue.add(Pair(y2,x2))
            tomato[y2][x2] = tomato[temp.first][temp.second] + 1
        }

    }
}