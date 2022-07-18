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

    var idx = str.length
    val stack = LinkedList<String>()

    for(item in str){
        stack.add(item.toString())
    }

    var st : StringTokenizer

    repeat(commandSize) {
        st = StringTokenizer(br.readLine())
        when(st.nextToken()){
            "L" ->  if(idx != 0 ) idx--

            "D" ->  if(idx != stack.size ) idx++

            "B" ->  if(idx != 0 ) stack.removeAt(--idx)

            "P" -> stack.add(idx++, st.nextToken())
        }
    }

    for(item in stack){
        sb.append(item)
    }

    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}
