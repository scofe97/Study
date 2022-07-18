package com.example.baekjoon.step_500_brute_force

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(){

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val number = br.readLine().toInt()
    val list = MutableList(number){ MutableList(2){0} }
    val rankList = MutableList(number){0}


    repeat(number){ idx ->
        val st = StringTokenizer(br.readLine())

        val weight = st.nextToken().toInt()
        val height = st.nextToken().toInt()

        list[idx][0] = weight
        list[idx][1] = height

    }

    for(i in 0 until number){
        for(j in 0 until number){
            if(j != i) if(list[i][0] < list[j][0] && list[i][1] < list[j][1]) rankList[i]++
        }
    }

    for(i in rankList){
        bw.write("${i+1} ")
    }

    bw.flush()
    bw.close()
    br.close()

}