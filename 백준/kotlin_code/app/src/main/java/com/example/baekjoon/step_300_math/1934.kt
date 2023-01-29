package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    val step = br.readLine().toInt()

    repeat(step) {
        val st = StringTokenizer(br.readLine())
        val num1 = st.nextToken().toInt()
        val num2 = st.nextToken().toInt()
        val result = if (num1 > num2) num1 * num2 / gcd(num1, num2) else num1 * num2 / gcd(num2, num1)
        sb.append(result).append("\n")
    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}