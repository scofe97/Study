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

    val str = br.readLine().toString()
    val commandSize = br.readLine().toInt()

    val stack : LinkedList<String> = LinkedList<String>()
    for (item in str) {
        stack.add(item.toString())
    }

    val iterator = stack.listIterator(str.length)

    repeat(commandSize) {
        val st = StringTokenizer(br.readLine())
        when (st.nextToken()) {
            "L" -> if (iterator.hasPrevious()) iterator.previous()

            "D" -> if (iterator.hasNext()) iterator.next()

            "B" -> {
                if (iterator.hasPrevious()) {
                    iterator.previous()
                    iterator.remove()
                }
            }

            "P" -> iterator.add(st.nextToken())
        }
    }

    for (item in stack) {
        sb.append(item)
    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}
