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

    val st = StringTokenizer(br.readLine())

    val num1 = st.nextToken().toInt()
    val num2 = st.nextToken().toInt()

    var gcd = if (num1 > num2) gcd(num1, num2) else gcd(num2, num1)
    var lcd = if (num1 > num2) num1 * num2 / gcd(num1, num2) else num1 * num2 / gcd(num2, num1)

    sb.append(gcd).append("\n")
    sb.append(lcd).append("\n")
    bw.write(sb.toString())
    bw.close()
    br.close()

}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a
    else gcd(b, a % b)
}