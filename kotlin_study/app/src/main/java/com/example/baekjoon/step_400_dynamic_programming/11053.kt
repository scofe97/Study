package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max


fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val count = br.readLine().toInt()
    val list : List<Int> = br.readLine().split(" ").map { it.toInt() }.toList()
    val listResult = MutableList(count){ _->1}

    for(i in 1 until count){
        for( n in 0 until i){
            if(list[i] > list[n]) listResult[i] = max(listResult[i], listResult[n]+1)
        }
    }

    val result = listResult.maxOrNull()
    bw.write(result.toString())

    bw.flush()
    bw.close()
    br.close()

}