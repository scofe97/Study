package com.example.baekjoon.basic

import java.util.*


fun dfs(graph: Array<Array<Int>>, visit: Array<Int>, x: Int) {

    visit[x] = 1
    print("$x ")

    for (i in 0 until 6) {
        if (visit[i] == 1 || graph[x][i] == 0) continue
        dfs(graph, visit, i)
    }
}

fun main() {

    var graph = Array(6) { Array(6) { 0 } }
    var visit = Array(6) { 0 }

    graph = arrayOf(
        arrayOf(0, 1, 0, 0, 1, 0),
        arrayOf(1, 0, 1, 1, 0, 0),
        arrayOf(0, 1, 0, 0, 0, 0),
        arrayOf(0, 1, 0, 0, 0, 0),
        arrayOf(1, 0, 0, 0, 0, 1),
        arrayOf(0, 0, 0, 0, 1, 0)
    )

    dfs(graph, visit, 0)
}