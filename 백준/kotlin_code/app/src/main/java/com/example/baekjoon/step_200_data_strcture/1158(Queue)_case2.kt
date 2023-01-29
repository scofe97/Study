package com.example.baekjoon.step_200_data_strcture

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    val st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()

    val queue = RingQueue<Int>(size)

    val sequenceNumber = st.nextToken().toInt()

    for (i in 1 until size * 2 + 1) {
        queue.push(i)
    }

    while (!queue.empty()) {
        sb.append(queue.pop(sequenceNumber)).append(", ")
    }

    sb.deleteCharAt(sb.length - 1)
    sb.deleteCharAt(sb.length - 1)
    sb.insert(0, "<")
    sb.insert(sb.length, ">")

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}