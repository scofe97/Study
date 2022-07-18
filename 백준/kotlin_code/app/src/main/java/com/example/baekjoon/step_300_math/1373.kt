package com.example.baekjoon.step_300_math

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.util.*

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    val st = StringTokenizer(br.readLine().reversed())
    val number = st.nextToken().chunked(3)

    var oct = 1
    var temp = 0

    for(str in number){
        temp = 0
        oct = 1

        for(i in str){
            if(i=='1') temp += oct
            oct *= 2
        }

        sb.append(temp)
    }


    bw.write(sb.reverse().toString())
    bw.flush()
    bw.close()
    br.close()
}