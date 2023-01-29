package com.example.baekjoon.step_200_data_strcture

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    val commandSize = br.readLine().toInt()
    val queue = ArrayListQueue2<Int>()

    repeat(commandSize) {
        val st = StringTokenizer(br.readLine())
        when(st.nextToken()){
            "push_front" ->  queue.pushFront(st.nextToken().toInt())

            "push_back" ->  queue.pushBack(st.nextToken().toInt())

            "pop_front" ->   sb.append((queue.popFront()?.toString() ?: "-1") +"\n")

            "pop_back" ->   sb.append((queue.popBack()?.toString() ?: "-1") +"\n")

            "size" ->  sb.append(queue.size().toString()+"\n")

            "empty" -> sb.append(queue.empty().toString() +"\n")

            "front" -> sb.append((queue.front()?.toString() ?:"-1") +"\n")

            "back" -> sb.append((queue.back()?.toString() ?:"-1") +"\n")
        }

    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}