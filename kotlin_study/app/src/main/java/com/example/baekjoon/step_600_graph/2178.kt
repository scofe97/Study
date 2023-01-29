package com.example.baekjoon.step_600_graph

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

// n x m 의 크기를 받는코드
private val st = StringTokenizer(br.readLine())
private val n = st.nextToken().toInt()
private val m = st.nextToken().toInt()

// BFS 에서 활용할 GRAPH, Visited
private val list : Array<IntArray> = Array(n){IntArray(m)}
private val visited = Array(n){BooleanArray(m)}

private var test = 0

private fun main() {

    // Graph 값 넣어줌
    repeat(n) { idx ->
        val line = br.readLine().map{it.digitToInt()}.toIntArray()
        list[idx] = line
    }


    bfs()
    println(test)
    bw.write("${list[n-1][m-1]}")
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs() {
    // 4방향으로 확인
    val dx = intArrayOf(1, 0, -1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    // BFS 에서 활용할 Queue, 최초시작점 0,0 offer
    val queue = LinkedList<Pair<Int, Int>>()
    queue.offer(Pair(0, 0))
    visited[0][0] = true

    while (queue.isNotEmpty()) {
        test++

        // queue 에서 꺼냄
        val temp = queue.poll()

        // x, y 값
        val tempX = temp.first
        val tempY = temp.second

        // 여기서 체크하면 시간이 더 많이듬
        // visited[tempX][tempY] = true

        // 4방향 움직임
        for (i in 0 until 4) {
            val x2 = tempX + dx[i]
            val y2 = tempY + dy[i]

            // 그래프 범위밖이거나 이미 방문했거나 0인부분은 생략
            if (x2 !in 0 until n || y2 !in 0 until m) continue
            if(list[x2][y2] == 0 || visited[x2][y2]) continue

            // 위의 조건을 통과하면 해당좌표를 넣어주고, +1해줌
            queue.offer(Pair(x2,y2))
            list[x2][y2] = list[tempX][tempY] + 1
            visited[x2][y2] = true
        }
    }
}