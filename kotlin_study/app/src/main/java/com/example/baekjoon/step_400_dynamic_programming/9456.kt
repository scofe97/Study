package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {

    // 입출력 버퍼 생성
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val count = br.readLine().toInt()
    val list = Array<IntArray>(2){IntArray(0)}


    repeat(count) {
        val count2 = br.readLine().toInt()

        repeat(2) { i ->
            val temp = br.readLine().split(" ").map { it -> it.toInt() }.toIntArray()
            list[i] = temp
        }

        for (i in 1 until count2) {

            if(i == 1){
                list[0][1] += list[1][0]
                list[1][1] += list[0][0]
            }

            else{
                list[0][i] += max(list[1][i-1], list[1][i-2] )
                list[1][i] += max(list[0][i-1], list[0][i-2] )
            }
        }

        val result = max(list[0].maxOrNull()!!,list[1].maxOrNull()!!)
        bw.write("$result \n")
    }


    bw.flush()
    bw.close()
    br.close()

}