package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val number = br.readLine().toInt()

    val input = Array(number) {
        val line = br.readLine()
        CharArray(number) {
            line[it]
        }
    }

    var answer = 0

    // 상하좌우 체크
    // 상 하 좌 우
    val dRow = arrayOf(0, 0, -1, 1)
    val dColumn = arrayOf(1, -1, 0, 0)

    for (row in 0 until number) {
        for (column in 0 until number) {
            for (d in 0 until 4) {

                val nr = row + dRow[d]
                val nc = column + dColumn[d]

                // 상하좌우가 배열 밖으로 나가는가?
                if (nr in 0 until number && nc in 0 until number) {

                    // 위치 바꾸기
                    val temp = input[row][column]
                    input[row][column] = input[nr][nc]
                    input[nr][nc] = temp


                    // 추가설명
                    // [column][row] -> 상하좌우에서 교체된 문자임
                    // 이 문자의 상하좌우에서 연속된 최대값을 구하는 과정

                    // 세로 탐색 -> [0][i] [1][i] ...
                    // ex [1][1] 로 교체된 문자가 왔다
                    // [0][1] [1][1] [2][1] ..
                    var result = 1
                    var char = input[0][column]

                    for (i in 1 until number) {


                        if (char == input[i][column]) {
                            // 만약 동일한 문자면 값을 1추가
                            result++
                        } else {
                            // 다른 문자이면 최대수열을 갱신함
                            // 그리고 1로 초기화하고 기준 문자열 변경
                            answer = max(answer, result)
                            result = 1
                            char = input[i][column]
                        }
                    }
                    // 4개 모두 동일한경우를 대비해 한번더 초기화
                    answer = max(answer, result)



                    // 가로 탐색
                    result = 1
                    char = input[row][0]


                    for (i in 1 until number) {
                        if (char == input[row][i]) {
                            result++
                        } else {
                            answer = max(answer, result)
                            result = 1
                            char = input[row][i]
                        }
                    }
                    answer = max(answer, result)


                    // 바꾼 위치 되돌리기
                    input[nr][nc] = input[row][column]
                    input[row][column] = temp
                }
            }
        }
    }

    bw.write(answer.toString())
    bw.flush()
    bw.close()
    br.close()

}


