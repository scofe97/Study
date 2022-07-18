package com.example.baekjoon.step_540_brute_force_bitmask

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.`out`))
private val st = StringTokenizer(br.readLine())
private val listSize = st.nextToken().toInt()
private val listSum = st.nextToken().toInt()
private val list = br.readLine().split(" ").map { it -> it.toInt() }.toIntArray()

fun main() {

    var ans = 0

    // 2진수 숫자 5개를 표현할수 있는 범위
    // 32 -> 100000(2)
    for (i in 1 until (1 shl listSize)) {
        var sum = 0
        for (k in 0 until listSize) {

            // 이부분이 헷갈릴거다
            // 모든 숫자의 경우의 수임
            // ex -> 10100, 00100 식으로 모든 숫자의 경우를 봄
            if (i and (1 shl k) != 0) {
                sum += list[k]
            }
        }
        if (sum == listSum) ans += 1
    }

    bw.write("$ans")
    bw.flush()
    bw.close()
    br.close()
}
