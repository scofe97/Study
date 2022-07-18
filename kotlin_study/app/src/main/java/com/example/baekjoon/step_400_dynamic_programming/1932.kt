package com.example.baekjoon.step_400_dynamic_programming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.max

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    val count = br.readLine().toInt()
    val list = Array<IntArray>(count){ IntArray(count) }
    var result : Int? = 0

    repeat(count){ i ->

        val st = StringTokenizer(br.readLine())

        for(j in 0..i){
            val number = st.nextToken().toInt()
            list[i][j] = number
        }
    }

    for(i in 0 until count){

        if(i == 0 ) {
            result = list[i][i]
            continue
        }

        for(j in 0..i){
            when(j){
                0 -> list[i][0] += list[i-1][0]
                i -> list[i][i] += list[i-1][i-1]
                else -> list[i][j] += max(list[i-1][j-1], list[i-1][j])
            }

        }
        result = list[i].maxOrNull()
    }

    bw.write(result.toString())
    bw.flush()
    bw.close()
    br.close()

}