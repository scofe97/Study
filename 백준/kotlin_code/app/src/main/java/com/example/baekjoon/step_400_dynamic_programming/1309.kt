package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    // 입출력 버퍼 생성
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))


    // 0 -> x x (사자가 없음)
    // 1 -> o x (왼쪽에 사자를 둠)
    // 2 -> x o (오른쪽에 사자를 둠)

    // ex) -> 3번째줄에 사자가 놓여질 수 있는 경우의 수
    //// 3번째 줄에 사자가 없다 ( 2번째줄에 사자가 왼쪽, 오른쪽, 없는 경우 모두 가능)
    //// 3번째 줄에 사자가 왼쪽에 있다 ( 2번째줄에 사자가 오른쪽, 없는 경우가 가능)
    //// 3번째 줄에 사자가 오른쪽에 있다 ( 2번째줄에 사자가 왼쪽, 없는 경우가 가능)


    val count = br.readLine().toInt()
    val list = Array<IntArray>(100001){IntArray(3)}

    list[0][0] = 1

    list[1][0] = 1
    list[1][1] = 1
    list[1][2] = 1

    for(i in 2..count){
        list[i][0] = (list[i-1][0] + list[i-1][1] + list[i-1][2]) % 9901
        list[i][1] = (list[i-1][0] + list[i-1][2]) % 9901
        list[i][2] = (list[i-1][0] + list[i-1][1]) % 9901
    }

    val result = (list[count][0] + list[count][1] + list[count][2]) % 9901

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()

}