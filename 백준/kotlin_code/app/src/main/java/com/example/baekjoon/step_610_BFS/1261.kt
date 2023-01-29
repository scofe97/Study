package com.example.baekjoon.step_610_BFS

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val st = StringTokenizer(br.readLine())
private val x = st.nextToken().toInt()
private val y = st.nextToken().toInt()
private val list = Array(y) { IntArray(x) }
private val visited = Array(y) { IntArray(x) {Integer.MAX_VALUE} }

private fun main() {

    repeat(y) { idx ->
        val line = br.readLine().map { it.digitToInt() }.toIntArray()
        list[idx] = line
    }

    bfs()
    bw.write("${visited[y-1][x-1]}")
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs() {
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    val start = Triple(0, 0, 0)
    val queue = LinkedList<Triple<Int, Int, Int>>()
    visited[0][0] = 0
    queue.offer(start)

    while (queue.isNotEmpty()) {
        val temp = queue.poll()!!

        val y2 = temp.first
        val x2 = temp.second
        val wall = temp.third

        for (i in 0 until 4) {
            val y3 = y2 + dy[i]
            val x3 = x2 + dx[i]

            if (x3 in 0 until x && y3 in 0 until y) {
                when (list[y3][x3]) {
                    0 -> {
                        if(visited[y3][x3] > wall){
                            visited[y3][x3] = wall
                            queue.offer(Triple(y3, x3, wall))
                        }
                    }
                    1 -> {
                        if(visited[y3][x3] > wall+1){
                            visited[y3][x3] = wall+1
                            queue.offer(Triple(y3, x3, wall+1))
                        }
                    }
                }
            }
        }
    }
}