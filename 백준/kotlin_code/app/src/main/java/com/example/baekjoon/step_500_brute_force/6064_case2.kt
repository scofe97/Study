package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    // M N x y
    val count = br.readLine().toInt()

    repeat(count) {
        var (m, n, x, y) = br.readLine().split(" ").map { it.toInt() }
        x--
        y--

        var res = -1
        var cnt = x
        while (cnt < n * m) {
            if (cnt % n == y) {
                res = cnt + 1
                break
            }

            cnt += m
        }

        bw.write("$res\n")
    }

    bw.flush()
    bw.close()
    br.close()

}

