package com.example.baekjoon.step_610_BFS

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/*

5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6

*/

// 간선 리스트
private lateinit var arr: ArrayList<ArrayList<Node>>

// 시작지점 거리판단
private lateinit var dist: IntArray

// 방문정점 체크
private lateinit var vis: BooleanArray

//  BFS 에 활용할 큐
private val queue = PriorityQueue<Node>()

private const val INF = 1000000000

private lateinit var st: StringTokenizer

private fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    // 정점과 간선의 개수
    st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val e = st.nextToken().toInt()

    arr = ArrayList()

    // 모든 정점에 ArrayList 삽입
    for (i in 0 until n) arr.add(ArrayList())

    // 시작점에서 모든 정점의 거리를 INF로 초기화
    dist = IntArray(n) { INF }

    // 방문체크 생성
    vis = BooleanArray(n)

    // 시작지점
    st = StringTokenizer(br.readLine())
    val k = st.nextToken().toInt() - 1

    // 간선 (시작, 끝, 가중치)
    for (i in 0 until e) {
        st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()-1
        val v = st.nextToken().toInt()-1
        val w = st.nextToken().toInt()
        arr[u].add(Node(v, w ))
    }

    dijkstra(k)

    // 각 정점간의 최단거리 출력
    dist.forEach {
        if (it == INF) {
            bw.write("INF")
            bw.write("\n")
        } else {
            bw.write(it.toString())
            bw.write("\n")
        }
    }
    bw.flush()
    bw.close()
}

private fun dijkstra(start: Int) {
    dist[start] = 0 // 시작 거리는 0
    queue.add(Node(start, 0)) // 시작 노드를 큐에 넣어줍니다

    while (queue.isNotEmpty()) {
        val curIndex = queue.peek().index  // 현재 노드 인덱스
        val curDist = queue.peek().dist  // 현재 노드까지의 거리
        queue.poll()

        if (dist[curIndex] < curDist) continue // 탐색 시간을 줄이기 위해
        // 현재 거리가 현재 노드까지의 거리보다 작으면 탐색 중단

        for (i in 0 until arr[curIndex].size) { // 연결된 노드들 탐색
            val nextIndex = arr[curIndex][i].index
            val nextDist = curDist + arr[curIndex][i].dist

            if (nextDist < dist[nextIndex]) {
                dist[nextIndex] = nextDist
                queue.add(Node(nextIndex, nextDist))
            }

        }
    }
}

data class Node(val index: Int, val dist: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = dist-other.dist
}
