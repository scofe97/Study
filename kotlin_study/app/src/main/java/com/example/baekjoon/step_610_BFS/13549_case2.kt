package com.example.baekjoon.step_610_BFS

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val st = StringTokenizer(br.readLine())
private val visited = IntArray(100001) {-1}

private fun main() {

    val start = st.nextToken().toInt()
    val end = st.nextToken().toInt()

    bfs(start, end)

    bw.flush()
    bw.close()
    br.close()

}

private fun bfs(start: Int, end: Int) {
    val queue = LinkedList<Int>()
    queue.offer(start)
    visited[start] = 0

    val arr = IntArray(3)

    while (queue.isNotEmpty()) {
        val temp = queue.poll()!!

        if (temp == end) {
            bw.write("${visited[temp]}")
            break
        }

        arr[0] = temp * 2
        arr[1] = temp - 1
        arr[2] = temp + 1

        for (i in 0 until 3) {
            if (arr[i] !in 0..100000 || visited[arr[i]] != -1) continue

            if (i != 0) {
                visited[arr[i]] = visited[temp] + 1
                queue.offer(arr[i])
            }
            else {
                visited[arr[i]] = visited[temp]
                queue.offer(arr[i])
            }

        }
    }
}