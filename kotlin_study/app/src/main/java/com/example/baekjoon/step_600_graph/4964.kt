package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

private val dx = intArrayOf(-1, 0, 1, 0, -1, -1, 1, 1)
private val dy = intArrayOf(0, -1, 0, 1, -1, 1, -1, 1)
private val result = mutableListOf<Int>()

private var size = 0
private lateinit var list: Array<IntArray>
private lateinit var visited: Array<BooleanArray>


private fun main() {

    while (true) {
        val st = StringTokenizer(br.readLine())

        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        if (x == 0 && y == 0) break

        list = Array<IntArray>(y) { IntArray(x) }
        visited = Array<BooleanArray>(y) { BooleanArray(x) }

        repeat(y) { idx ->
            val line = br.readLine().split(" ").map { it.toInt() }.toIntArray()
            list[idx] = line
        }

        for (i in 0 until y) {
            for (j in 0 until x) {
                if (list[i][j] == 1 && !visited[i][j]) {
                    bfs(j, i, x, y)
                }

            }
        }
        result.add(size)
        size = 0
    }

    for (i in result) {
        bw.write("$i\n")
    }
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(x: Int, y: Int, sizeX: Int, sizeY: Int) {

    val point = Point(x, y)
    size++

    val queue = LinkedList<Point>()
    queue.add(point)

    while (queue.isNotEmpty()) {

        val temp = queue.poll()!!

        for (i in 0 until 8) {
            val x2 = temp.x + dx[i]
            val y2 = temp.y + dy[i]

            if (x2 !in 0 until sizeX || y2 !in 0 until sizeY) continue

            if (list[y2][x2] == 1 && !visited[y2][x2]) {
                visited[y2][x2] = true
                queue.add(Point(x2, y2))
            }
        }

    }
}

private data class Point(val x: Int, val y: Int)