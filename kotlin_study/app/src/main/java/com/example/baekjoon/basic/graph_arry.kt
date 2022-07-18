package com.example.baekjoon.basic

// 그래프 소개
// Queue 탐색 (DFS/ BFS)
// Bipartite Graph
// Topological Order

fun main(){

    // 2차원 배열로 Graph 생성

     val graph = arrayOf(
        arrayOf(0,1,0,1,0),
        arrayOf(1,0,1,0,0),
        arrayOf(0,1,0,0,0),
        arrayOf(1,0,0,0,1),
        arrayOf(0,1,0,1,0)
    )

    // index 와 value 를 동시에 받는다.
    for((index1, adjacent) in graph.withIndex()){

        // 각 Vertex 를 말한다
        val criteriaNode  = when(index1){
            0 -> 'A'
            1 -> 'B'
            2 -> 'C'
            3 -> 'D'
            4 -> 'E'
            else -> 'X'
        }

        // 기준 노드 출력
        print("$criteriaNode : ")

        // Vertex 의 Edge 검사
        for((index2,connected) in adjacent.withIndex()){

            if(connected == 1){
                val connectedNode = when(index2){
                    0 -> 'A'
                    1 -> 'B'
                    2 -> 'C'
                    3 -> 'D'
                    4 -> 'E'
                    else -> 'X'
                }
                print("$connectedNode ")
            }
        }
        println()
    }
}