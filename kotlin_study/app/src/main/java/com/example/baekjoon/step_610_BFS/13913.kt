package com.example.baekjoon.step_610_BFS

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val st = StringTokenizer(br.readLine())
private val visited = BooleanArray(100001)
private val step = IntArray(100001)
private val result = mutableListOf<Int>()

private fun main() {

    val start = st.nextToken().toInt()
    val end = st.nextToken().toInt()

    bfs(start,end)

    bw.flush()
    bw.close()
    br.close()

}

private fun bfs(start: Int, end: Int) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.offer(Pair(start, 0))

    while (queue.isNotEmpty()) {
        val temp = queue.poll()!!
        val move = mutableListOf<Int>()

        if(temp.first == end){
            bw.write("${temp.second}\n")

            var s = temp.first
            result.add(s)
            while(s != start){
                s = step[s]
                result.add(s)
            }

            result.reverse()

            for(i in result){
                bw.write("$i ")
            }

            break
        }

        move.add(temp.first + 1)
        move.add(temp.first - 1)
        move.add(temp.first * 2)

        for(i in 0 until 3){
            if(move[i] !in 0..100000 || visited[move[i]]) continue
            visited[move[i]] = true
            step[move[i]] = temp.first
            queue.offer(Pair(move[i],temp.second+1))
        }

        move.clear()
    }
}