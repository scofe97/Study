package com.example.baekjoon.step_510_brute_force_back_tracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.system.exitProcess

private var map = Array(9) { IntArray(9) }
private var list: LinkedList<Node> = LinkedList()

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

fun main() {

    for(i in 0..8){
        val st = StringTokenizer(br.readLine())

        for(j in 0..8){
            map[i][j] = st.nextToken().toInt()

            // 0이면 Node 에 x,y 좌표를 저장한다.
            // list 의 크기만큼 dfs 를 돌아주면 됨
            if(map[i][j] == 0) list.add(Node(i,j))
        }
    }
    dfs(0)
}

private fun dfs(depth: Int) {

    // 0의 개수만큼 탐색이 완료됨
    if (depth == list.size) {
        for (i in 0..8) {
            for (j in 0..8) {
                bw.write("${map[i][j]} ")
            }
            bw.write("\n")
        }
        bw.flush()
        bw.close()
        br.close()
        exitProcess(0)
    }

    // 0인 부분의 위치를 x y 에 넣어줌
    val node = list[depth]
    val x = node.x
    val y = node.y


    // 빈칸인 map[x][y]에 1~9 값 되는지 check
    for (i in 1..9) {
        if (check(x, y, i)) {
            //되는 숫자면 넣고 이어서 탐색
            map[x][y] = i
            dfs(depth + 1)
            map[x][y] = 0
        }
    }
}

private fun check(x: Int, y: Int, num: Int): Boolean {

    // 0 인지 아닌지
    if (map[x][y] != 0) return false


    // 가로, 세로줄 중복검사
    for (i in 0..8) { //가로,세로줄 중복검사
        if (map[i][y] == num || map[x][i] == num)  return false
    }


    // 0 3 6 -> 패턴생성
    val x2 = x / 3 * 3
    val y2 = y / 3 * 3

    // 3x3 중복체크
    for (i in x2 until x2 + 3) {
        for (j in y2 until y2 + 3) {
            if (map[i][j] == num) return false
        }
    }

    return true
}


private data class Node(var x: Int, var y: Int)