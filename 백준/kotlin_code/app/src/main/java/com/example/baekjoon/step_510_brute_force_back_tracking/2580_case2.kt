package com.example.baekjoon.step_510_brute_force_back_tracking

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.system.exitProcess

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))

//사전 순으로 앞서는 것을 출력 -> 행 박스 열 순이 유리
//위 설명 x 그냥 순서를 1부터 9까지 탐색함으로써 자동으로 사전순 정렬
private val square =  Array(9) { BooleanArray(10) }
private val row =  Array(9) { BooleanArray(10) }
private val col =  Array(9) { BooleanArray(10) }
private var finish = false

//backtracking
fun play(idx : Int, graph : Array<IntArray>){

    var idxCopy = idx
    var r = 0
    var c = 0
    while(idxCopy<81){
        r = idxCopy/9
        c = idxCopy%9
        if(graph[r][c]==0)
            break
        idxCopy++
    }

    if(idxCopy==81){
        finish= true
        return
    }

    for (i in 1..9) {

        //해당 칸에 숫자가 들어갈 수 없으면 다음 숫자 검사
        if (row[r][i]) continue
        if (col[c][i]) continue
        if (square[((r / 3) * 9 + c) / 3][i]) continue

        //들어갈 수 있다면 넣기
        graph[r][c] = i
        row[r][i] = true
        square[((r / 3) * 9 + c) / 3][i] = true
        col[c][i] = true

        //다음 0인 칸 찾기
        play(idx + 1, graph)

        //이전 dfs 에서 종료되었다면 싹 다 return 으로 종료
        if (finish) return

        //해당 칸에 i를 넣고 다음 0들을 검사했을 때 스도쿠가 완성될 수 없다면 다시 초기화
        //return 이전에 넣으면그래프가 다시 초기화 됨에 유의
        graph[r][c] = 0
        row[r][i] = false
        square[((r / 3) * 9 + c) / 3][i] = false
        col[c][i] = false
    }


}

fun main() {
    //input
    val graph = Array(9) { r ->
        val tk = StringTokenizer(br.readLine())

        IntArray(9) { c ->
            val num = tk.nextToken().toInt()
            //row
            row[r][num] = true
            //col
            col[c][num] = true
            //square
            square[((r / 3) * 9 + c) / 3][num] = true
            num
        }
    }

    //solve
    play(0,graph)

    //answer
    for(i in 0 until 9){
        for(j in 0 until 9){
            bw.write("${graph[i][j]} ")
        }
        bw.write("\n")
    }
    bw.flush()
    bw.close()
    br.close()
}

