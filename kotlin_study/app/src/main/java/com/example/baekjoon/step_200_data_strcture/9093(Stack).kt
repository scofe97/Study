package com.example.baekjoon.step_200_data_strcture

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var st = StringTokenizer(br.readLine())
    val commandSize = st.nextToken().toInt()

    repeat(commandSize){
        val str = mutableListOf<String>()
        st =  StringTokenizer(br.readLine())

        while(st.hasMoreTokens()){
            str.add(st.nextToken().reversed())
        }

        for(item in str){
            bw.write("$item ")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
    br.close()
}
