package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    val st = StringTokenizer(br.readLine())
    val minNum = st.nextToken().toInt()
    val maxNum = st.nextToken().toInt()+1

    val decimal = MutableList<Int>(maxNum){i -> i}
    var count = 0

    decimal[0] = -1
    decimal[1] = -1

    for(i in 2 until maxNum){

        if(decimal[i]==0) continue
        for(t in i*2 until maxNum step i) decimal[t] = -1

    }

    for(i in minNum until maxNum){
        if(decimal[i] != -1) sb.append(decimal[i]).append("\n")
    }


    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}