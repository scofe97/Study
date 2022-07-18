package com.example.baekjoon.step_200_data_strcture

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()
    val commandSize = br.readLine().toInt()

    val stack = mutableListOf<Int>()
    var start = 0
    var number = 0
    var found = true

    repeat(commandSize) {
        number = br.readLine().toInt()

        if(number > start){
            while(number != start){
                start++
                stack.add(start)
                sb.append("+\n")
            }
        }else if(number != stack.last()){
            found = false
        }

        stack.removeLast()
        sb.append("-\n")
    }

    if(found){
        bw.write(sb.toString())
        bw.flush()
    }else{
        sb.clear()
        sb.append("NO")
        bw.write(sb.toString())
        bw.flush()
    }

    bw.close()
    br.close()
}



