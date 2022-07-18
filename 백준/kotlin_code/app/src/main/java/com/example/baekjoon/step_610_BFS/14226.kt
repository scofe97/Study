package com.example.baekjoon.step_610_BFS

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val count = br.readLine().toInt()
private val visited = Array(1001) { BooleanArray(1001) }
private var result = 0

private fun main() {

    bfs()

    bw.write("$result")
    bw.flush()
    bw.close()
    br.close()

}

private fun bfs() {
    val queue = LinkedList<Triple<Int, Int, Int>>()

    var screen = 0
    var clipBoard = 0
    var time = 0
    val start = Triple(1, 0,0)

    queue.offer(start)
    visited[1][0] = false

    while (queue.isNotEmpty()) {
        val temp = queue.poll()!!

        screen = temp.first
        clipBoard = temp.second
        time = temp.third

        if (screen == count) {
            result = time
            return
        }

        if(screen + clipBoard !in 0..1000) continue

        if (clipBoard > 0 && !visited[screen + clipBoard][clipBoard]) {
            visited[screen + clipBoard][clipBoard] = true
            queue.offer(Triple(screen + clipBoard, clipBoard, time+1))
        }
        if (screen > 0 && !visited[screen - 1][clipBoard]) {
            visited[screen - 1][clipBoard] = true
            queue.offer(Triple(screen - 1, clipBoard, time+1))
        }

        if (clipBoard <= count && !visited[screen][screen]) {
            visited[screen][screen] = true
            queue.offer(Triple(screen, screen, time+1))
        }
    }
}