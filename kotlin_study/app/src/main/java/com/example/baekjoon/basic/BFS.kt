package com.example.baekjoon.basic

import java.util.*

fun bfs(graph: Array<Array<Int>>, visit: Array<Int>, x: Int) {

    var temp = 0
    val queue : Queue<Int> = LinkedList()

    queue.add(x)
    visit[x] = 1

    while(queue.isNotEmpty()){
        temp = queue.poll()
        print("$temp ")

        for(i in 0 until 6){
            if(visit[i] == 0 && graph[temp][i] == 1){

                queue.add(i)
                visit[i] = 1
            }
        }
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

    bfs(graph, visit, 0)
}