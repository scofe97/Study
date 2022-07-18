package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*
import kotlin.math.sqrt

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()


    val step = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())

    val decimal = MutableList<Int>(1000){i -> i}
    var count = 0

    decimal[0] = -1
    decimal[1] = -1

    for(i in 2 until sqrt(1000.0).toInt()){

        if(decimal[i]==0) continue
        for(t in i*2 until 1000 step i) decimal[t] = -1

    }

    repeat(step){
        val temp = st.nextToken().toInt()
        if(temp in decimal) count++
    }

    sb.append(count)
    bw.write(sb.toString())
    bw.flush()
    bw.close()
    br.close()
}