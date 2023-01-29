package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()

private lateinit var list: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private var result = 0

private fun main() {
    solution()

    bw.flush()
    bw.close()
    br.close()
}

private fun solution() {

    repeat(count) {
        val size = br.readLine().toInt()

        var st = StringTokenizer(br.readLine())
        val startX = st.nextToken().toInt()
        val startY = st.nextToken().toInt()

        st = StringTokenizer(br.readLine())
        val endX = st.nextToken().toInt()
        val endY = st.nextToken().toInt()

        list = Array(size) { IntArray(size) { 0 } }
        visited = Array(size) { BooleanArray(size) }

        bfs(startX, startY, endX, endY, size)

        bw.write("$result\n")
        result = 0
    }
}

private fun bfs(x: Int, y: Int, endX: Int, endY: Int, size: Int) {
    val dx = intArrayOf(-2, -1, 1, 2, -2, -1, 1, 2)
    val dy = intArrayOf(-1, -2, -2, -1, 1, 2, 2, 1)

    val queue = LinkedList<Pair<Int, Int>>()
    queue.offer(Pair(x, y))
    visited[x][y] = true

    while (queue.isNotEmpty()) {
        val temp = queue.poll()!!

        if (temp.first == endX && temp.second == endY){
            result = list[temp.first][temp.second]
            return
        }

        for (i in 0 until 8) {
            val x2 = temp.first + dx[i]
            val y2 = temp.second + dy[i]

            if (x2 !in 0 until size || y2 !in 0 until size) continue
            if (visited[x2][y2]) continue

            visited[x2][y2] = true
            queue.add(Pair(x2,y2))
            list[x2][y2] = list[temp.first][temp.second] + 1
        }
    }
}