package com.example.baekjoon.step_200_data_strcture

import java.io.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val st = br.readLine()

    var bar = 0
    var barSum = 0
    var i = 0

    while (i < st.length) {
        when (st[i]) {
            '(' -> {
                if (st[i + 1] == ')') {
                    // 레이저 발생 -> 지금 생성된 막대기만큼 잘림
                    barSum += bar
                    // 레이저이므로 다음단계 생략
                    i++
                } else bar++
            }
            ')' -> {
                barSum++
                bar--
            }
        }
        i++
    }
    bw.write("$barSum")
    bw.flush()
    bw.close()
    br.close()
}