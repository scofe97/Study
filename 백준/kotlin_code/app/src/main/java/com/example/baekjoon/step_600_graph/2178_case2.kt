package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var matrix: Array<IntArray> = arrayOf()
private var visited: Array<BooleanArray> = arrayOf()
private var nm: List<Int> = listOf()

data class Dot(val x: Int, val y: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    nm = readLine().split(" ").map { it.toInt() }

    matrix = Array(nm[0]) { IntArray(nm[1]) }
        .map { readLine().map{ it.toInt() - 48 }.toIntArray()
        }.toTypedArray()

    visited = Array(nm[0]) { BooleanArray(nm[1]) { false } }

    visited[0][0] = true
    bfs(0, 0)
    println(matrix[nm[0] - 1][nm[1] - 1])

}

private fun bfs(x: Int, y: Int) {
    val dx = intArrayOf(1,-1,0,0)
    val dy = intArrayOf(0,0,-1,1)
    val list: LinkedList<Dot> = LinkedList()
    list.add(Dot(x, y))

    while (list.isNotEmpty()) {

        val now = list.poll()

        for (i in 0 until 4) {
            val nx = now.x + dx[i]
            val ny = now.y + dy[i]

            if (nx !in 0 until nm[0] || ny !in 0 until nm[1]) continue
            if (matrix[nx][ny] == 0 || visited[nx][ny]) continue

            list.add(Dot(nx, ny))
            matrix[nx][ny] = matrix[now.x][now.y] + 1
            visited[nx][ny] = true

        }
    }
}