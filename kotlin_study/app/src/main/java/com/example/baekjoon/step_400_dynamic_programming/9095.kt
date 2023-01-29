package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val count = br.readLine().toInt()
    val list = MutableList(11){ _-> 0}
    list[1] = 1
    list[2] = 2
    list[3] = 4

    repeat(count){
        val number = br.readLine().toInt()

        for(i in 4..number){
            if(list[i] != 0  ) continue
            list[i] = list[i-1] + list[i-2] + list[i-3]
        }

        bw.write("${list[number]} \n")
    }


    bw.flush()
    bw.close()
    br.close()
}