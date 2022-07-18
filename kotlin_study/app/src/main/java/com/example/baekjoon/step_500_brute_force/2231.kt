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
    var num = 1
    while (num < number){
        val temp = num.toString()
        var result = num

        for(i in temp){
            result += i.digitToInt()
        }

        if(result == number) break

        num++
    }

    if(num == number) bw.write("0") else bw.write("$num")

    bw.flush()
    bw.close()
    br.close()
}
