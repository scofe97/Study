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

    val count = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())

    val list = arrayListOf<Int>()
    val result = IntArray(count)

    for(i in 0 until count){
        result[i] = st.nextToken().toInt()
    }

    repeat(count) { step ->
        while (list.isNotEmpty() && result[list.last()] < result[step]) {
            result[list.last()] = result[step]
            list.removeLast()
        }
        list.add(step)

    }


    while (list.isNotEmpty()) {
        result[list.last()] = -1
        list.removeLast()
    }

    for(i in 0 until count){
        sb.append(result[i]).append(" ")
    }

    sb.deleteCharAt(sb.length - 1)
    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}